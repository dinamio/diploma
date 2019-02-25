package com.university.contractors.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class EducationProgram implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educ_prog")
    private Long id;

    private String educProgName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEducProgName() {
        return educProgName;
    }

    public void setEducProgName(String educProgName) {
        this.educProgName = educProgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationProgram that = (EducationProgram) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
