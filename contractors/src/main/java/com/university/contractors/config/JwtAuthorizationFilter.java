package com.university.contractors.config;

import com.university.contractors.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.university.contractors.config.SecurityConstants.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final ContractorsUserDetailService contractorsUserDetailService;

    JwtAuthorizationFilter(AuthenticationManager authenticationManager, ContractorsUserDetailService contractorsUserDetailService) {
        super(authenticationManager);
        this.contractorsUserDetailService = contractorsUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String authorizationHeaderValue = request.getHeader(HEADER_NAME);

        if (hasInvalidHeaderValue(authorizationHeaderValue)) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthenticationToken(request);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_NAME);

        if (Objects.isNull(token)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request should contain header with token.");
        }

        final String username = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        UserDetails userDetails = contractorsUserDetailService.loadUserByUsername(username);
        User user = contractorsUserDetailService.loadCustomUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities());
    }

    private boolean hasInvalidHeaderValue(String authorizationHeaderValue) {
        return Objects.isNull(authorizationHeaderValue) || !authorizationHeaderValue.startsWith(TOKEN_PREFIX);
    }
}
