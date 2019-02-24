package com.university.contractors.service;

import com.university.contractors.model.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.university.contractors.config.SecurityConstants.TOKEN_PREFIX;
import static org.apache.commons.lang3.StringUtils.SPACE;

@Service
public class AuthorizationService {

    private final ContractorsUserDetailService contractorsUserDetailService;
    private final TokenParser tokenParser;

    @Autowired
    public AuthorizationService(ContractorsUserDetailService contractorsUserDetailService,
                                TokenParser tokenParser) {
        this.contractorsUserDetailService = contractorsUserDetailService;
        this.tokenParser = tokenParser;
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String headerValue) {
        final String token = getTokenFromHeaderValue(headerValue);

        final String username = tokenParser.getUsernameFormToken(token);
        final UserDetails userDetails = contractorsUserDetailService.loadUserByUsername(username);
        final User user = contractorsUserDetailService.loadCustomUserByUsername(username); // todo add token validation

        return new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities());
    }

    private String getTokenFromHeaderValue(String headerValue) {
        final String[] prefixAndToken = headerValue.split(SPACE);

        if (ObjectUtils.notEqual(prefixAndToken.length, 2)) {
            throw new MalformedTokenException();
        }

        final String prefix = prefixAndToken[0];
        final String token = prefixAndToken[1];

        if (ObjectUtils.notEqual(prefix, TOKEN_PREFIX)) {
            throw new MalformedTokenException();
        }

        return token;
    }
}
