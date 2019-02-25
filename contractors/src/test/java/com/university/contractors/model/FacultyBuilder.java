package com.university.contractors.model;

public final class FacultyBuilder {
    private Faculty faculty;

    private FacultyBuilder() {
        faculty = new Faculty();
    }

    public static FacultyBuilder aFaculty() {
        return new FacultyBuilder();
    }

    public FacultyBuilder id(Long id) {
        faculty.setId(id);
        return this;
    }

    public FacultyBuilder facultyName(String facultyName) {
        faculty.setFacultyName(facultyName);
        return this;
    }

    public FacultyBuilder facultyNameShort(String facultyNameShort) {
        faculty.setFacultyNameShort(facultyNameShort);
        return this;
    }

    public FacultyBuilder facultyNameEng(String facultyNameEng) {
        faculty.setFacultyNameEng(facultyNameEng);
        return this;
    }

    public FacultyBuilder facultyNameRu(String facultyNameRu) {
        faculty.setFacultyNameRu(facultyNameRu);
        return this;
    }

    public Faculty build() {
        return faculty;
    }
}
