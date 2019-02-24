package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.StudentStatus;
import com.university.contractors.model.StudentStatusBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StudentStatusControllerTest extends AbstractCrudControllerTest<Long, StudentStatus> {

    @Override
    Set<StudentStatus> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(StudentStatus[].class)).collect(Collectors.toSet());
    }

    @Override
    StudentStatus getArbitraryEntity() {
        return StudentStatusBuilder.aStudentStatus()
                .studentStatusName("StudentStatusName-" + UUID.randomUUID().toString())
                .build();
    }

    @Override
    void assertEntities(StudentStatus expected, StudentStatus actual) {
        assertEquals(expected.getStudentStatusName(), actual.getStudentStatusName());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.STUDENT_STATUSES;
    }

    @Override
    protected Class<StudentStatus> getEntityType() {
        return StudentStatus.class;
    }
}