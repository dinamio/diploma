package com.university.contractors.repository;

import com.university.contractors.model.PaymentReason;
import org.springframework.data.repository.CrudRepository;

public interface PaymentReasonRepository extends CrudRepository<PaymentReason, Long> {
}
