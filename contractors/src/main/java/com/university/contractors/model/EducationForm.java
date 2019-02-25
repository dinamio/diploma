package com.university.contractors.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class EducationForm implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educ_form")
    private Long id;

    private String educFormName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEducFormName() {
        return educFormName;
    }

    public void setEducFormName(String educFormName) {
        this.educFormName = educFormName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationForm that = (EducationForm) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(educFormName, that.educFormName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, educFormName);
    }
}
