package com.university.contractors.service;

import com.university.contractors.config.ContractorsUserDetailService;
import com.university.contractors.config.TokenParser;
import com.university.contractors.model.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        final User user = contractorsUserDetailService.loadCustomUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities());
    }

    private String getTokenFromHeaderValue(String headerValue) {
        if (hasInvalidHeaderValue(headerValue)) {
            throw new AuthorizationHeaderNotFound();
        }

        final String[] prefixAndToken = headerValue.split(SPACE);

        if (ObjectUtils.notEqual(prefixAndToken.length, 2)) {
            throw new AuthorizationHeaderMalformedValueException(headerValue);
        }

        final String prefix = prefixAndToken[0];
        final String token = prefixAndToken[1];

        if (ObjectUtils.notEqual(prefix, TOKEN_PREFIX)) {
            throw new AuthorizationInvalidPrefixException();
        }

        return token;
    }

    private boolean hasInvalidHeaderValue(String authorizationHeaderValue) {
        return Objects.isNull(authorizationHeaderValue) || !authorizationHeaderValue.startsWith(TOKEN_PREFIX);
    }
}
