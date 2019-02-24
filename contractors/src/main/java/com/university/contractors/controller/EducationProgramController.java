package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.EducationProgram;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class EducationProgramController extends AbstractCrudControllerBase<Long, EducationProgram> {

    public EducationProgramController(CrudRepository<EducationProgram, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    @GetMapping(path = Endpoints.EDUCATION_PROGRAM_BY_ID)
    public EducationProgram getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @GetMapping(path = Endpoints.EDUCATION_PROGRAMS)
    public Iterable<EducationProgram> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(path = Endpoints.EDUCATION_PROGRAMS)
    public EducationProgram create(@RequestBody EducationProgram entityToCreate) {
        return super.create(entityToCreate);
    }

    @Override
    @PutMapping(path = Endpoints.EDUCATION_PROGRAM_BY_ID)
    public EducationProgram update(@PathVariable Long id, @RequestBody EducationProgram entityToUpdateWith) {
        return super.update(id, entityToUpdateWith);
    }

    @Override
    @DeleteMapping(path = Endpoints.EDUCATION_PROGRAM_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
