package com.university.contractors.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.stereotype.Component;

import static com.university.contractors.config.SecurityConstants.SECRET;

@Component
public class TokenParser {

    public String getUsernameFormToken(String token) throws MalformedJwtException {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
