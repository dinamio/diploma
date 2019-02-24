package com.university.contractors.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class EducationLevel implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educ_level")
    private Long id;

    private String educationLevelName;
    private Integer numberOfMonth;
    private Boolean isSummerMonth;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEducationLevelName() {
        return educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName) {
        this.educationLevelName = educationLevelName;
    }

    public Integer getNumberOfMonth() {
        return numberOfMonth;
    }

    public void setNumberOfMonth(Integer numberOfMonth) {
        this.numberOfMonth = numberOfMonth;
    }

    public Boolean getSummerMonth() {
        return isSummerMonth;
    }

    public void setSummerMonth(Boolean summerMonth) {
        isSummerMonth = summerMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationLevel that = (EducationLevel) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
