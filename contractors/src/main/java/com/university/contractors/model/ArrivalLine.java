package com.university.contractors.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class ArrivalLine implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arrival_line")
    private Long id;

    private String arrivalCenter;
    private String arrivalCenterName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getArrivalCenter() {
        return arrivalCenter;
    }

    public void setArrivalCenter(String arrivalCenter) {
        this.arrivalCenter = arrivalCenter;
    }

    public String getArrivalCenterName() {
        return arrivalCenterName;
    }

    public void setArrivalCenterName(String arrivalCenterName) {
        this.arrivalCenterName = arrivalCenterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrivalLine that = (ArrivalLine) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
