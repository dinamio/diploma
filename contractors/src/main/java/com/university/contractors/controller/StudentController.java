package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Student;
import com.university.contractors.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class StudentController {

    private final StudentRepository studentRepository; // todo add service layer

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(path = Endpoints.STUDENT_BY_ID)
    public Student getById(@PathVariable Long id) {
        Optional<Student> optionalStudentById = studentRepository.findById(id);

        if (optionalStudentById.isPresent()) {
            return optionalStudentById.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id '%s' was not found.");
    }

    @GetMapping(path = Endpoints.STUDENTS)
    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    @PostMapping(path = Endpoints.STUDENTS)
    public Student create(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping(path = Endpoints.STUDENTS)
    public Student update(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping(path = Endpoints.STUDENT_BY_ID)
    public void delete(@PathVariable Long id) {
        boolean isStudentWithGivenIdExists = studentRepository.existsById(id);

        if (isStudentWithGivenIdExists) {
            studentRepository.deleteById(id);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id '%s' was not found.");
    }
}
