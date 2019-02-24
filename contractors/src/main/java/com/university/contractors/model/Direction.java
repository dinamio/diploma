package com.university.contractors.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class Direction implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direction")
    private Long id;

    private String directionName;

    @ManyToOne
    @JoinColumn(name = "ref_country")
    private Faculty faculty;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return Objects.equal(id, direction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
