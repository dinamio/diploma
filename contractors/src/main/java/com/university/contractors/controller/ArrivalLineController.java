package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.ArrivalLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArrivalLineController extends AbstractCrudControllerBase<Long, ArrivalLine> {

    public ArrivalLineController(CrudRepository<ArrivalLine, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    @GetMapping(path = Endpoints.ARRIVAL_LINE_BY_ID)
    public ArrivalLine getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @GetMapping(path = Endpoints.ARRIVAL_LINES)
    public Iterable<ArrivalLine> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(path = Endpoints.ARRIVAL_LINES)
    public ArrivalLine create(@RequestBody ArrivalLine entityToCreate) {
        return super.create(entityToCreate);
    }

    @Override
    @PutMapping(path = Endpoints.ARRIVAL_LINE_BY_ID)
    public ArrivalLine update(@PathVariable Long id, @RequestBody ArrivalLine entityToUpdateWith) {
        return super.update(id, entityToUpdateWith);
    }

    @Override
    @DeleteMapping(path = Endpoints.ARRIVAL_LINE_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
