package com.university.contractors.repository;

import com.university.contractors.model.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {

    List<Country> findByCountryNameEng(String countryNameEng);
}
