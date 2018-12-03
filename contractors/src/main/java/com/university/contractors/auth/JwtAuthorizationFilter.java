package com.university.contractors.auth;


import com.university.contractors.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.university.contractors.auth.AuthConstants.*;

@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Pattern TOKEN_PREFIX_PATTERN = Pattern.compile(TOKEN_PREFIX, Pattern.LITERAL);

    private final UserDetailsServiceImpl customUserDetailService;

    @Autowired
    public JwtAuthorizationFilter(AuthenticationManager authManager, UserDetailsServiceImpl userDetailsService) {
        super(authManager);
        this.customUserDetailService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);

        if (Objects.isNull(header) || !header.startsWith(TOKEN_PREFIX)) { // todo change to predicate
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);

        if (Objects.isNull(authenticationToken)) {
            logger.warn("Access denied.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        logger.info("Access granted for user: {}.");

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (Objects.isNull(token)) {
            // todo fix NULL return value, change it to exception.
            return null;
        }

        String username = Jwts.parser().setSigningKey(SIGNING_KEY)
                .parseClaimsJws(TOKEN_PREFIX_PATTERN.matcher(token).replaceAll(Matcher.quoteReplacement("")))
                .getBody()
                .getSubject();

        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);


        if (Objects.isNull(username)) {
            // todo fix NULL return value, change it to exception.
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
