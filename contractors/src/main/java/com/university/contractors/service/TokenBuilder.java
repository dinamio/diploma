package com.university.contractors.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

import static com.university.contractors.config.SecurityConstants.SECRET;

@Service
public class TokenBuilder {

    public String buildToken(String username, ZonedDateTime expirationTime) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(expirationTime.toInstant()))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
