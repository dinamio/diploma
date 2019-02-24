package com.university.contractors.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class Faculty implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_faculty")
    private Long id;

    private String facultyName;
    private String facultyNameShort;
    private String facultyNameEng;
    private String facultyNameRu;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyNameShort() {
        return facultyNameShort;
    }

    public void setFacultyNameShort(String facultyNameShort) {
        this.facultyNameShort = facultyNameShort;
    }

    public String getFacultyNameEng() {
        return facultyNameEng;
    }

    public void setFacultyNameEng(String facultyNameEng) {
        this.facultyNameEng = facultyNameEng;
    }

    public String getFacultyNameRu() {
        return facultyNameRu;
    }

    public void setFacultyNameRu(String facultyNameRu) {
        this.facultyNameRu = facultyNameRu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equal(id, faculty.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
