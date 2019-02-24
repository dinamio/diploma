package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class FacultyController extends AbstractCrudControllerBase<Long, Faculty> {

    public FacultyController(CrudRepository<Faculty, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    @GetMapping(path = Endpoints.FACULTY_BY_ID)
    public Faculty getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @GetMapping(path = Endpoints.FACULTIES)
    public Iterable<Faculty> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(path = Endpoints.FACULTIES)
    public Faculty create(@RequestBody Faculty entityToCreate) {
        return super.create(entityToCreate);
    }

    @Override
    @PutMapping(path = Endpoints.FACULTY_BY_ID)
    public Faculty update(@PathVariable Long id, @RequestBody Faculty entityToUpdateWith) {
        return super.update(id, entityToUpdateWith);
    }

    @Override
    @DeleteMapping(path = Endpoints.FACULTY_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
