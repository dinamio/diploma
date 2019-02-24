package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController extends AbstractCrudControllerBase<Long, Country> {

    public CountryController(CrudRepository<Country, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    @GetMapping(path = Endpoints.COUNTRY_BY_ID)
    public Country getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @GetMapping(path = Endpoints.COUNTRIES)
    public Iterable<Country> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(path = Endpoints.COUNTRIES)
    public Country create(@RequestBody Country entityToCreate) {
        return super.create(entityToCreate);
    }

    @Override
    @PutMapping(path = Endpoints.COUNTRY_BY_ID)
    public Country update(@PathVariable Long id, @RequestBody Country entityToUpdateWith) {
        return super.update(id, entityToUpdateWith);
    }

    @Override
    @DeleteMapping(path = Endpoints.COUNTRY_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
