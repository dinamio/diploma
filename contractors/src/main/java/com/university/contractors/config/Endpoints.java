package com.university.contractors.config;

public interface Endpoints {

    String API_PREFIX = "/api";
    String API_VERSION_PREFIX = "/v1";
    String ENDPOINTS_PREFIX = API_PREFIX + API_VERSION_PREFIX;

    String LOGIN = ENDPOINTS_PREFIX + "/login";
    String SIGN_UP = ENDPOINTS_PREFIX + "/signUp";

    String ENTITY_PREFIX = ENDPOINTS_PREFIX + "/entity";
    String ID_PARAMETER = "/{id}";

    String STUDENTS = ENTITY_PREFIX + "/students";
    String STUDENT_BY_ID = STUDENTS + ID_PARAMETER;

    String COUNTRIES = ENTITY_PREFIX + "/countries";
    String COUNTRY_BY_ID = COUNTRIES + ID_PARAMETER;

    String ARRIVAL_LINES = ENTITY_PREFIX + "/arrival_lines";
    String ARRIVAL_LINE_BY_ID = ARRIVAL_LINES + ID_PARAMETER;

    String PAYMENT_REASONS = ENTITY_PREFIX + "/payment_reasons";
    String PAYMENT_REASON_BY_ID = PAYMENT_REASONS + ID_PARAMETER;
}
