package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Faculty;
import com.university.contractors.model.FacultyBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class FacultyControllerTest extends AbstractCrudControllerTest<Long, Faculty> {

    @Override
    Set<Faculty> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(Faculty[].class)).collect(Collectors.toSet());
    }

    @Override
    Faculty getArbitraryEntity() {
        return FacultyBuilder.aFaculty()
                .facultyName("FacultyName-" + UUID.randomUUID().toString())
                .facultyNameShort("FacultyNameShort-" + UUID.randomUUID().toString())
                .facultyNameRu("facultyNameRu-" + UUID.randomUUID().toString())
                .facultyNameEng("facultyNameEng-" + UUID.randomUUID().toString())
                .build();
    }

    @Override
    void assertEntities(Faculty expected, Faculty actual) {
        assertEquals(expected.getFacultyName(), actual.getFacultyName());
        assertEquals(expected.getFacultyNameShort(), actual.getFacultyNameShort());
        assertEquals(expected.getFacultyNameRu(), actual.getFacultyNameRu());
        assertEquals(expected.getFacultyNameEng(), actual.getFacultyNameEng());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.FACULTIES;
    }

    @Override
    protected Class<Faculty> getEntityType() {
        return Faculty.class;
    }
}