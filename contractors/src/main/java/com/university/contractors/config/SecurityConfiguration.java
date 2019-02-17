package com.university.contractors.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.contractors.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ContractorsUserDetailService contractorsUserDetailService;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    @Autowired
    public SecurityConfiguration(ContractorsUserDetailService contractorsUserDetailService,
                                 BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.contractorsUserDetailService = contractorsUserDetailService;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .cors().and().csrf().disable().authorizeRequests()

                .antMatchers(Endpoints.LOGIN).permitAll()
                .antMatchers(Endpoints.SIGN_UP).permitAll()
                .antMatchers(Endpoints.ENTITY_PREFIX + "/**").hasAnyAuthority(UserRole.USER.getValue(), UserRole.ADMIN.getValue())

                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), new ObjectMapper()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), contractorsUserDetailService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(contractorsUserDetailService)
                .passwordEncoder(bcryptPasswordEncoder);
    }
}