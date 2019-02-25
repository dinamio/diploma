package com.university.contractors.model;

public final class PaymentReasonBuilder {
    private PaymentReason paymentReason;

    private PaymentReasonBuilder() {
        paymentReason = new PaymentReason();
    }

    public static PaymentReasonBuilder aPaymentReason() {
        return new PaymentReasonBuilder();
    }

    public PaymentReasonBuilder id(Long id) {
        paymentReason.setId(id);
        return this;
    }

    public PaymentReasonBuilder paymentReasonName(String paymentReasonName) {
        paymentReason.setPaymentReasonName(paymentReasonName);
        return this;
    }

    public PaymentReasonBuilder paymentType(PaymentType paymentType) {
        paymentReason.setPaymentType(paymentType);
        return this;
    }

    public PaymentReason build() {
        return paymentReason;
    }
}
