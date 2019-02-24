package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Direction;
import com.university.contractors.model.DirectionBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class DirectionControllerTest extends AbstractCrudControllerTest<Long, Direction> {

    @Override
    Set<Direction> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(Direction[].class)).collect(Collectors.toSet());
    }

    @Override
    Direction getArbitraryEntity() {
        return DirectionBuilder.aDirection()
                .directionName("Direction-" + UUID.randomUUID().toString())
                .faculty(null) // todo set value
                .build();
    }

    @Override
    void assertEntities(Direction expected, Direction actual) {
        assertEquals(expected.getDirectionName(), actual.getDirectionName());
        assertEquals(expected.getFaculty(), actual.getFaculty());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.DIRECTIONS;
    }

    @Override
    protected Class<Direction> getEntityType() {
        return Direction.class;
    }
}