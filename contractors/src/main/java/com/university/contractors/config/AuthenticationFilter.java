package com.university.contractors.config;

import com.university.contractors.controller.payload.LoginUser;
import com.university.contractors.service.AuthenticationService;
import com.university.contractors.service.EntityParseException;
import com.university.contractors.service.EntityParser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final EntityParser entityParser;
    private final AuthenticationService authenticationService;


    AuthenticationFilter(AuthenticationManager authenticationManager,
                         EntityParser entityParser, AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.entityParser = entityParser;
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            final LoginUser user = entityParser.parseEntityFromRequest(request, LoginUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (EntityParseException exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponseBody(response, exception.getMessage());
            throw new BadAuthenticationRequest(exception.getMessage(), exception);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        final String username = ((UserDetails) authResult.getPrincipal()).getUsername();
        final String token = authenticationService.processSuccessfulAuthentication(username);
        response.getWriter().write(token);
    }

    private void writeResponseBody(HttpServletResponse response, String body) {
        try {
            response.getWriter().write(body);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write response body", e);
        }
    }
}
