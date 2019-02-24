package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.ArrivalLine;
import com.university.contractors.model.ArrivalLineBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ArrivalLineControllerTest extends AbstractCrudControllerTest<Long, ArrivalLine> {

    @Override
    Set<ArrivalLine> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(ArrivalLine[].class)).collect(Collectors.toSet());
    }

    @Override
    ArrivalLine getArbitraryEntity() {
        return ArrivalLineBuilder.anArrivalLine()
                .arrivalCenter("ArrivalCenter-" + UUID.randomUUID().toString())
                .arrivalCenterName("ArrivalCenterName-" + UUID.randomUUID().toString())
                .build();
    }

    @Override
    void assertEntities(ArrivalLine expected, ArrivalLine actual) {
        assertEquals(expected.getArrivalCenter(), actual.getArrivalCenter());
        assertEquals(expected.getArrivalCenterName(), actual.getArrivalCenterName());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.ARRIVAL_LINES;
    }

    @Override
    protected Class<ArrivalLine> getEntityType() {
        return ArrivalLine.class;
    }
}