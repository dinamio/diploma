package com.university.contractors.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class PaymentReason implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_reason")
    private Long id;

    private String paymentReasonName;

    @ManyToOne
    @JoinColumn(name = "ref_payment_type")
    private PaymentType paymentType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentReasonName() {
        return paymentReasonName;
    }

    public void setPaymentReasonName(String paymentReasonName) {
        this.paymentReasonName = paymentReasonName;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentReason that = (PaymentReason) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
