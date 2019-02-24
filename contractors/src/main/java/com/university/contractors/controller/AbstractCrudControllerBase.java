package com.university.contractors.controller;

import com.university.contractors.model.IdEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;


public abstract class AbstractCrudControllerBase<I, E extends IdEntity<I>> {

    private final CrudRepository<E, I> crudRepository;

    public AbstractCrudControllerBase(CrudRepository<E, I> crudRepository) {
        this.crudRepository = crudRepository;
    }

    public E getById(I id) {
        Optional<E> optionalEntityById = crudRepository.findById(id);

        if (!optionalEntityById.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Entity with ID '%s' was not found.");
        }

        return optionalEntityById.get();
    }

    public Iterable<E> getAll() {
        return crudRepository.findAll();
    }

    public E create(E entityToCreate) {
        return crudRepository.save(entityToCreate);
    }

    public E update(I id, E entityToUpdateWith) {
        final I entityId = entityToUpdateWith.getId();

        if (Objects.isNull(entityId)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Entity to update should have ID.");
        }

        if (ObjectUtils.notEqual(id, entityId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ID of Entity and ID specified in the request path should match.");
        }

        return crudRepository.save(entityToUpdateWith);
    }

    public void delete(I id) {
        boolean isEntityWithGivenIdExists = crudRepository.existsById(id);

        if (!isEntityWithGivenIdExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Entity with ID '%s' was not found.");
        }

        crudRepository.deleteById(id);
    }
}
