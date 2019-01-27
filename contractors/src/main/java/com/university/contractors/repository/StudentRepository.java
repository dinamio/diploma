package com.university.contractors.repository;

import com.university.contractors.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Override
    Iterable<Student> findAll();

    @Override
    <S extends Student> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
