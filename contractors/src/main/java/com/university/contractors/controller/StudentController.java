package com.university.contractors.controller;

import com.university.contractors.model.Student;
import com.university.contractors.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @PutMapping
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        studentRepository.deleteById(id);
    }
}
