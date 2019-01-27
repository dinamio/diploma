package com.university.contractors.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Student")
    private Long id;

    @Column(name = "StudSurname")
    private String surname;

    @Column(name = "StudName")
    private String name;

    @Column(name = "StudMiddlename")
    private String middleName;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "Ref_Country")
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
