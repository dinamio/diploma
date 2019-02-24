package com.university.contractors.config;

import com.university.contractors.service.AuthorizationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final AuthorizationService authorizationService;

    AuthorizationFilter(AuthenticationManager authenticationManager, AuthorizationService authorizationService) {
        super(authenticationManager);
        this.authorizationService = authorizationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String authorizationHeaderValue = request.getHeader(SecurityConstants.HEADER_NAME);

        if (Objects.nonNull(authorizationHeaderValue)) {
            authorizeRequest(authorizationHeaderValue);
        }

        chain.doFilter(request, response);
    }

    private void authorizeRequest(String authorizationHeaderValue) {
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                authorizationService.getAuthenticationToken(authorizationHeaderValue);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
