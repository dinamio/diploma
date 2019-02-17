package com.university.contractors.config;

public interface Endpoints {

    String API_PREFIX = "/api";
    String ENTITY_PREFIX = API_PREFIX + "/entity";

    String STUDENTS = ENTITY_PREFIX + "/students";
    String STUDENT_BY_ID = STUDENTS + "/{id}";

    String LOGIN = API_PREFIX + "/login";
    String SIGN_UP = API_PREFIX + "/signUp";

}
