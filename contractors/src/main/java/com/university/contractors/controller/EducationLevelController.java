package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.EducationLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class EducationLevelController extends AbstractCrudControllerBase<Long, EducationLevel> {

    public EducationLevelController(CrudRepository<EducationLevel, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    @GetMapping(path = Endpoints.EDUCATION_LEVEL_BY_ID)
    public EducationLevel getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @GetMapping(path = Endpoints.EDUCATION_LEVELS)
    public Iterable<EducationLevel> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(path = Endpoints.EDUCATION_LEVELS)
    public EducationLevel create(@RequestBody EducationLevel entityToCreate) {
        return super.create(entityToCreate);
    }

    @Override
    @PutMapping(path = Endpoints.EDUCATION_LEVEL_BY_ID)
    public EducationLevel update(@PathVariable Long id, @RequestBody EducationLevel entityToUpdateWith) {
        return super.update(id, entityToUpdateWith);
    }

    @Override
    @DeleteMapping(path = Endpoints.EDUCATION_LEVEL_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
