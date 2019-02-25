package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.PaymentReason;
import com.university.contractors.model.PaymentReasonBuilder;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PaymentReasonControllerTest extends AbstractCrudControllerTest<Long, PaymentReason> {

    @Override
    Set<PaymentReason> extractEntitiesSet(Response response) {
        return Arrays.stream(response.as(PaymentReason[].class)).collect(Collectors.toSet());
    }

    @Override
    PaymentReason getArbitraryEntity() {
        return PaymentReasonBuilder.aPaymentReason()
                .paymentReasonName("PaymentReasonName-" + UUID.randomUUID().toString())
                .paymentType(null) // todo set value;
                .build();
    }

    @Override
    void assertEntities(PaymentReason expected, PaymentReason actual) {
        assertEquals(expected.getPaymentReasonName(), actual.getPaymentReasonName());
        assertEquals(expected.getPaymentType(), actual.getPaymentType());
    }

    @Override
    protected String getEndpoint() {
        return Endpoints.PAYMENT_REASONS;
    }

    @Override
    protected Class<PaymentReason> getEntityType() {
        return PaymentReason.class;
    }
}