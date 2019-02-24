package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Country;
import com.university.contractors.model.CountryBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class CountryControllerTest extends AbstractCrudControllerTest<Long, Country> {

    @Override
    Set<Country> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(Country[].class)).collect(Collectors.toSet());
    }

    @Override
    Country getArbitraryEntity() {
        return CountryBuilder.aCountry()
                .countryNameEng("EngName-" + UUID.randomUUID().toString())
                .countryNameUa("UaName-" + UUID.randomUUID().toString())
                .countryNameRu("RuName-" + UUID.randomUUID().toString())
                .build();
    }

    @Override
    void assertEntities(Country expected, Country actual) {
        assertEquals("Expected student surname doesn't match actual.",
                expected.getCountryNameEng(), actual.getCountryNameEng());
        assertEquals("Expected student name doesn't match actual.",
                expected.getCountryNameUa(), actual.getCountryNameUa());
        assertEquals("Expected student surname doesn't match actual.",
                expected.getCountryNameRu(), actual.getCountryNameRu());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.COUNTRIES;
    }

    @Override
    protected Class<Country> getEntityType() {
        return Country.class;
    }
}