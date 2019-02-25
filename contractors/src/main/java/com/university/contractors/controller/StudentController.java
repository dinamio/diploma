package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Student;
import com.university.contractors.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController extends AbstractCrudControllerBase<Long, Student> {

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        super(studentRepository);
    }

    @GetMapping(path = Endpoints.STUDENT_BY_ID)
    public Student getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @GetMapping(path = Endpoints.STUDENTS)
    public Iterable<Student> getAll() {
        return super.getAll();
    }

    @PostMapping(path = Endpoints.STUDENTS)
    public Student create(@RequestBody Student student) {
        return super.create(student);
    }

    @PutMapping(path = Endpoints.STUDENT_BY_ID)
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return super.update(id, student);
    }

    @DeleteMapping(path = Endpoints.STUDENT_BY_ID)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }
}
