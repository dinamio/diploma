package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.EducationProgram;
import com.university.contractors.model.EducationProgramBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class EducationProgramControllerTest extends AbstractCrudControllerTest<Long, EducationProgram> {

    @Override
    Set<EducationProgram> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(EducationProgram[].class)).collect(Collectors.toSet());
    }

    @Override
    EducationProgram getArbitraryEntity() {
        return EducationProgramBuilder.anEducationProgram()
                .educProgName("EducationProgramName-" + UUID.randomUUID().toString())
                .build();
    }

    @Override
    void assertEntities(EducationProgram expected, EducationProgram actual) {
        assertEquals(expected.getEducProgName(), actual.getEducProgName());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.EDUCATION_PROGRAMS;
    }

    @Override
    protected Class<EducationProgram> getEntityType() {
        return EducationProgram.class;
    }
}