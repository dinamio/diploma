package com.university.contractors.model;

public final class EducationLevelBuilder {
    private EducationLevel educationLevel;

    private EducationLevelBuilder() {
        educationLevel = new EducationLevel();
    }

    public static EducationLevelBuilder anEducationLevel() {
        return new EducationLevelBuilder();
    }

    public EducationLevelBuilder id(Long id) {
        educationLevel.setId(id);
        return this;
    }

    public EducationLevelBuilder educationLevelName(String educationLevelName) {
        educationLevel.setEducationLevelName(educationLevelName);
        return this;
    }

    public EducationLevelBuilder numberOfMonth(Integer numberOfMonth) {
        educationLevel.setNumberOfMonth(numberOfMonth);
        return this;
    }

    public EducationLevelBuilder isSummerMonth(Boolean isSummerMonth) {
        educationLevel.setSummerMonth(isSummerMonth);
        return this;
    }

    public EducationLevel build() {
        return educationLevel;
    }
}
