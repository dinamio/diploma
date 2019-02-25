package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.EducationLevel;
import com.university.contractors.model.EducationLevelBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class EducationLevelControllerTest extends AbstractCrudControllerTest<Long, EducationLevel> {

    @Override
    Set<EducationLevel> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(EducationLevel[].class)).collect(Collectors.toSet());
    }

    @Override
    EducationLevel getArbitraryEntity() {
        return EducationLevelBuilder.anEducationLevel()
                .educationLevelName("EducationLevelName-" + UUID.randomUUID().toString())
                .numberOfMonth(new Random().nextInt(24) + 1)
                .isSummerMonth(new Random().nextBoolean())
                .build();
    }

    @Override
    void assertEntities(EducationLevel expected, EducationLevel actual) {
        assertEquals(expected.getEducLevelName(), actual.getEducLevelName());
        assertEquals(expected.getNumberOfMonth(), actual.getNumberOfMonth());
        assertEquals(expected.getSummerMonth(), actual.getSummerMonth());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.EDUCATION_LEVELS;
    }

    @Override
    protected Class<EducationLevel> getEntityType() {
        return EducationLevel.class;
    }
}
