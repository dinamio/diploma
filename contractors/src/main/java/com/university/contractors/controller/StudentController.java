package com.university.contractors.controller;

import com.university.contractors.config.Endpoints;
import com.university.contractors.model.Student;
import com.university.contractors.repository.StudentRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@RestController
public class StudentController { // TODO: 20/02/19 add AbstractController.

    private final StudentRepository studentRepository; // todo add service layer

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(path = Endpoints.STUDENT_BY_ID)
    public Student getById(@PathVariable Long id) {
        Optional<Student> optionalStudentById = studentRepository.findById(id);

        if (!optionalStudentById.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Student with ID '%s' was not found.");
        }

        return optionalStudentById.get();
    }

    @GetMapping(path = Endpoints.STUDENTS)
    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    @PostMapping(path = Endpoints.STUDENTS)
    public Student create(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping(path = Endpoints.STUDENT_BY_ID)
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        final Long studentId = student.getId();

        if (Objects.isNull(studentId)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Student to update should have ID.");
        }

        if (ObjectUtils.notEqual(id, studentId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ID of Student and ID specified in the request path should match.");
        }

        return studentRepository.save(student);
    }

    @DeleteMapping(path = Endpoints.STUDENT_BY_ID)
    public void delete(@PathVariable Long id) {
        boolean isStudentWithGivenIdExists = studentRepository.existsById(id);

        if (!isStudentWithGivenIdExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Student with ID '%s' was not found.");
        }

        studentRepository.deleteById(id);
    }
}
