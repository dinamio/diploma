package com.university.contractors.config;

public interface Endpoints {

    String API_PREFIX = "/api";
    String API_VERSION_PREFIX = "/v1";
    String ENDPOINTS_PREFIX = API_PREFIX + API_VERSION_PREFIX;
    String ENTITY_PREFIX = ENDPOINTS_PREFIX + "/entity";

    String STUDENTS = ENTITY_PREFIX + "/students";
    String STUDENT_BY_ID = STUDENTS + "/{id}";

    String LOGIN = ENDPOINTS_PREFIX + "/login";
    String SIGN_UP = ENDPOINTS_PREFIX + "/signUp";

}
