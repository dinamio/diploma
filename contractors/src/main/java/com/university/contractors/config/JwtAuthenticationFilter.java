package com.university.contractors.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.contractors.controller.payload.LoginUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.university.contractors.config.SecurityConstants.DEFAULT_EXPIRATION_TIME;
import static com.university.contractors.config.SecurityConstants.SECRET;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    JwtAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        setFilterProcessesUrl(Endpoints.LOGIN);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try { // TODO: 20/02/19 move exception handling to aspect by making this filter a component
            final LoginUser user = parseUserFromJson(request);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        final ZonedDateTime expirationTime = ZonedDateTime.now(ZoneOffset.UTC).plus(DEFAULT_EXPIRATION_TIME, ChronoUnit.MILLIS);

        final String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
                .setExpiration(Date.from(expirationTime.toInstant()))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        response.getWriter().write(token); // TODO: 18/02/19 add token saving to database.
    }

    private LoginUser parseUserFromJson(HttpServletRequest request) throws IOException {
        return objectMapper.readValue(request.getInputStream(), LoginUser.class);
    }
}
