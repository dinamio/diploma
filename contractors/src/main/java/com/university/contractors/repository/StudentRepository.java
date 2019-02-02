package com.university.contractors.repository;

import com.university.contractors.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends CrudRepository<Student, Long> {

}
