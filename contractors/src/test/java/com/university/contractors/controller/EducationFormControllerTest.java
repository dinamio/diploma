package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.EducationForm;
import com.university.contractors.model.EducationFormBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class EducationFormControllerTest extends AbstractCrudControllerTest<Long, EducationForm> {

    @Override
    Set<EducationForm> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(EducationForm[].class)).collect(Collectors.toSet());
    }

    @Override
    EducationForm getArbitraryEntity() {
        return EducationFormBuilder.anEducationForm()
                .educFormName("EducationFormName-" + UUID.randomUUID().toString())
                .build();
    }

    @Override
    void assertEntities(EducationForm expected, EducationForm actual) {
        assertEquals(expected.getEducFormName(), actual.getEducFormName());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.EDUCATION_FORMS;
    }

    @Override
    protected Class<EducationForm> getEntityType() {
        return EducationForm.class;
    }
}