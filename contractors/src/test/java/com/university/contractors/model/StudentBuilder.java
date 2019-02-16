package com.university.contractors.model;

import java.util.Date;

public final class StudentBuilder {
    private Student student;

    private StudentBuilder() {
        student = new Student();
    }

    public static StudentBuilder aStudent() {
        return new StudentBuilder();
    }

    public StudentBuilder id(Long id) {
        student.setId(id);
        return this;
    }

    public StudentBuilder surname(String surname) {
        student.setSurname(surname);
        return this;
    }

    public StudentBuilder name(String name) {
        student.setName(name);
        return this;
    }

    public StudentBuilder middleName(String middleName) {
        student.setMiddleName(middleName);
        return this;
    }

    public StudentBuilder dateOfBirth(Date dateOfBirth) {
        student.setDateOfBirth(dateOfBirth);
        return this;
    }

    public StudentBuilder country(Country country) {
        student.setCountry(country);
        return this;
    }

    public Student build() {
        return student;
    }
}
