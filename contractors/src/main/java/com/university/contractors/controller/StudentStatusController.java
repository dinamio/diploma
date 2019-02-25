package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.StudentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentStatusController extends AbstractCrudControllerBase<Long, StudentStatus> {

    public StudentStatusController(CrudRepository<StudentStatus, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    @GetMapping(path = Endpoints.STUDENT_STATUS_BY_ID)
    public StudentStatus getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @GetMapping(path = Endpoints.STUDENT_STATUSES)
    public Iterable<StudentStatus> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(path = Endpoints.STUDENT_STATUSES)
    public StudentStatus create(@RequestBody StudentStatus entityToCreate) {
        return super.create(entityToCreate);
    }

    @Override
    @PutMapping(path = Endpoints.STUDENT_STATUS_BY_ID)
    public StudentStatus update(@PathVariable Long id, @RequestBody StudentStatus entityToUpdateWith) {
        return super.update(id, entityToUpdateWith);
    }

    @Override
    @DeleteMapping(path = Endpoints.STUDENT_STATUS_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
