package com.university.contractors.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class EntityParser {

    private final ObjectMapper objectMapper;

    @Autowired
    public EntityParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public <E> E parseEntityFromRequest(HttpServletRequest request, Class<E> entityClass) {
        try {
            return objectMapper.readValue(request.getInputStream(), entityClass);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
