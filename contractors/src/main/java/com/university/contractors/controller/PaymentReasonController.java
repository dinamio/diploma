package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.PaymentReason;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentReasonController extends AbstractCrudControllerBase<Long, PaymentReason> {

    public PaymentReasonController(CrudRepository<PaymentReason, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    @GetMapping(path = Endpoints.PAYMENT_REASON_BY_ID)
    public PaymentReason getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @GetMapping(path = Endpoints.PAYMENT_REASONS)
    public Iterable<PaymentReason> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(path = Endpoints.PAYMENT_REASONS)
    public PaymentReason create(@RequestBody PaymentReason entityToCreate) {
        return super.create(entityToCreate);
    }

    @Override
    @PutMapping(path = Endpoints.PAYMENT_REASON_BY_ID)
    public PaymentReason update(@PathVariable Long id, @RequestBody PaymentReason entityToUpdateWith) {
        return super.update(id, entityToUpdateWith);
    }

    @Override
    @DeleteMapping(path = Endpoints.PAYMENT_REASON_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
