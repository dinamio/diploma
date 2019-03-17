package com.university.contractors.config;

public interface Endpoints {

    // Prefixes
    String API_PREFIX = "/api";
    String API_VERSION_PREFIX = "/v1";
    String ENDPOINTS_PREFIX = API_PREFIX + API_VERSION_PREFIX;

    String ENTITY_PREFIX = ENDPOINTS_PREFIX + "/entity";
    String ID_PARAMETER = "/{id}";

    // Login/SignUp
    String LOGIN = ENDPOINTS_PREFIX + "/login";
    String SIGN_UP = ENDPOINTS_PREFIX + "/signUp";

    // Search
    String SEARCH = ENDPOINTS_PREFIX + "/search";

    // CRUD entities
    String STUDENTS = ENTITY_PREFIX + "/students";
    String STUDENT_BY_ID = STUDENTS + ID_PARAMETER;

    String COUNTRIES = ENTITY_PREFIX + "/countries";
    String COUNTRY_BY_ID = COUNTRIES + ID_PARAMETER;

    String ARRIVAL_LINES = ENTITY_PREFIX + "/arrival_lines";
    String ARRIVAL_LINE_BY_ID = ARRIVAL_LINES + ID_PARAMETER;

    String PAYMENT_REASONS = ENTITY_PREFIX + "/payment_reasons";
    String PAYMENT_REASON_BY_ID = PAYMENT_REASONS + ID_PARAMETER;

    String STUDENT_STATUSES = ENTITY_PREFIX + "/student_statuses";
    String STUDENT_STATUS_BY_ID = STUDENT_STATUSES + ID_PARAMETER;

    String EDUCATION_PROGRAMS = ENTITY_PREFIX + "/education_programs";
    String EDUCATION_PROGRAM_BY_ID = EDUCATION_PROGRAMS + ID_PARAMETER;

    String EDUCATION_FORMS = ENTITY_PREFIX + "/education_forms";
    String EDUCATION_FORM_BY_ID = EDUCATION_FORMS + ID_PARAMETER;

    String EDUCATION_LEVELS = ENTITY_PREFIX + "/education_levels";
    String EDUCATION_LEVEL_BY_ID = EDUCATION_LEVELS + ID_PARAMETER;

    String FACULTIES = ENTITY_PREFIX + "/faculties";
    String FACULTY_BY_ID = FACULTIES + ID_PARAMETER;

    String DIRECTIONS = ENTITY_PREFIX + "/directions";
    String DIRECTION_BY_ID = DIRECTIONS + ID_PARAMETER;
}
