package com.university.contractors.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.stereotype.Service;

import static com.university.contractors.config.SecurityConstants.SECRET;

@Service
public class TokenParser {

    public String getUsernameFormToken(String token) throws MalformedJwtException {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
