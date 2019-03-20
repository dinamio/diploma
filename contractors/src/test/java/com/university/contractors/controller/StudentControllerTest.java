package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Student;
import com.university.contractors.model.StudentBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StudentControllerTest extends AbstractCrudControllerTest<Long, Student> {

    @Override
    Set<Student> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(Student[].class)).collect(Collectors.toSet());
    }

    @Override
    Student getArbitraryEntity() {
        return StudentBuilder.aStudent().name("John" + UUID.randomUUID())
                .surname("Smith" + UUID.randomUUID())
                .middleName("Mihailovich" + UUID.randomUUID())
                .country(null) // todo set value
                .build();
    }

    @Override
    void assertEntities(Student expected, Student actual) {
        assertEquals("Expected student surname doesn't match actual.",
                expected.getSurname(), actual.getSurname());
        assertEquals("Expected student name doesn't match actual.",
                expected.getName(), actual.getName());
        assertEquals("Expected student surname doesn't match actual.",
                expected.getMiddleName(), actual.getMiddleName());
        assertEquals("Expected student date of birth doesn't match actual.",
                expected.getDateOfBirth(), actual.getDateOfBirth());
        assertEquals("Expected student country of birth doesn't match actual.",
                expected.getCountry(), actual.getCountry());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.STUDENTS;
    }

    @Override
    protected Class<Student> getEntityType() {
        return Student.class;
    }
}