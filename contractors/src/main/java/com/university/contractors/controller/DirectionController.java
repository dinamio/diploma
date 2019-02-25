package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Direction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class DirectionController extends AbstractCrudControllerBase<Long, Direction> {

    public DirectionController(CrudRepository<Direction, Long> crudRepository) {
        super(crudRepository);
    }

    @Override
    @GetMapping(path = Endpoints.DIRECTION_BY_ID)
    public Direction getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @GetMapping(path = Endpoints.DIRECTIONS)
    public Iterable<Direction> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(path = Endpoints.DIRECTIONS)
    public Direction create(@RequestBody Direction entityToCreate) {
        return super.create(entityToCreate);
    }

    @Override
    @PutMapping(path = Endpoints.DIRECTION_BY_ID)
    public Direction update(@PathVariable Long id, @RequestBody Direction entityToUpdateWith) {
        return super.update(id, entityToUpdateWith);
    }

    @Override
    @DeleteMapping(path = Endpoints.DIRECTION_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
