package com.university.contractors.config;

import com.university.contractors.model.UserRole;
import com.university.contractors.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ContractorsUserDetailService contractorsUserDetailService;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final AuthorizationService authorizationService;
    private final AuthenticationService authenticationService;
    private final EntityParser entityParser;

    @Autowired
    public SecurityConfiguration(ContractorsUserDetailService contractorsUserDetailService,
                                 BCryptPasswordEncoder bcryptPasswordEncoder,
                                 AuthorizationService authorizationService,
                                 AuthenticationService authenticationService,
                                 EntityParser entityParser) {
        this.contractorsUserDetailService = contractorsUserDetailService;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
        this.authorizationService = authorizationService;
        this.authenticationService = authenticationService;
        this.entityParser = entityParser;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(),
                entityParser, authenticationService);
        authenticationFilter.setFilterProcessesUrl(Endpoints.LOGIN);

        http.exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .cors().and().csrf().disable().authorizeRequests()

                .antMatchers(Endpoints.LOGIN).permitAll()
                .antMatchers(Endpoints.SIGN_UP).permitAll()
                .antMatchers(Endpoints.ENTITY_PREFIX + "/**").hasAnyAuthority(UserRole.USER.getValue(), UserRole.ADMIN.getValue())

                .and()
                .addFilter(authenticationFilter)
                .addFilter(new AuthorizationFilter(authenticationManager(), authorizationService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(contractorsUserDetailService)
                .passwordEncoder(bcryptPasswordEncoder);
    }
}