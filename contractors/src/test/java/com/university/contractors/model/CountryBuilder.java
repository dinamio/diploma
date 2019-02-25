package com.university.contractors.model;

public final class CountryBuilder {
    private Country country;

    private CountryBuilder() {
        country = new Country();
    }

    public static CountryBuilder aCountry() {
        return new CountryBuilder();
    }

    public CountryBuilder id(Long id) {
        country.setId(id);
        return this;
    }

    public CountryBuilder countryNameUa(String countryNameUa) {
        country.setCountryNameUa(countryNameUa);
        return this;
    }

    public CountryBuilder countryNameEng(String countryNameEng) {
        country.setCountryNameEng(countryNameEng);
        return this;
    }

    public CountryBuilder countryNameRu(String countryNameRu) {
        country.setCountryNameRu(countryNameRu);
        return this;
    }

    public Country build() {
        return country;
    }
}
