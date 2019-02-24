package com.university.contractors.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class Country implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    private Long id;

    private String countryNameUa;
    private String countryNameEng;
    private String countryNameRu;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryNameUa() {
        return countryNameUa;
    }

    public void setCountryNameUa(String countryNameUa) {
        this.countryNameUa = countryNameUa;
    }

    public String getCountryNameEng() {
        return countryNameEng;
    }

    public void setCountryNameEng(String countryNameEng) {
        this.countryNameEng = countryNameEng;
    }

    public String getCountryNameRu() {
        return countryNameRu;
    }

    public void setCountryNameRu(String countryNameRu) {
        this.countryNameRu = countryNameRu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equal(id, country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
