package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.EducationForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class EducationFormController extends AbstractCrudControllerBase<Long, EducationForm> {

    public EducationFormController(CrudRepository<EducationForm, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    @GetMapping(path = Endpoints.EDUCATION_FORM_BY_ID)
    public EducationForm getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @GetMapping(path = Endpoints.EDUCATION_FORMS)
    public Iterable<EducationForm> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(path = Endpoints.EDUCATION_FORMS)
    public EducationForm create(@RequestBody EducationForm entityToCreate) {
        return super.create(entityToCreate);
    }

    @Override
    @PutMapping(path = Endpoints.EDUCATION_FORM_BY_ID)
    public EducationForm update(@PathVariable Long id, @RequestBody EducationForm entityToUpdateWith) {
        return super.update(id, entityToUpdateWith);
    }

    @Override
    @DeleteMapping(path = Endpoints.EDUCATION_FORM_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
