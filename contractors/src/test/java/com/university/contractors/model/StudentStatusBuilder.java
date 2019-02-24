package com.university.contractors.model;

public final class StudentStatusBuilder {
    private StudentStatus studentStatus;

    private StudentStatusBuilder() {
        studentStatus = new StudentStatus();
    }

    public static StudentStatusBuilder aStudentStatus() {
        return new StudentStatusBuilder();
    }

    public StudentStatusBuilder id(Long id) {
        studentStatus.setId(id);
        return this;
    }

    public StudentStatusBuilder studentStatusName(String studentStatusName) {
        studentStatus.setStudentStatusName(studentStatusName);
        return this;
    }

    public StudentStatus build() {
        return studentStatus;
    }
}
