package com.university.contractors.model;

public final class EducationFormBuilder {
    private EducationForm educationForm;

    private EducationFormBuilder() {
        educationForm = new EducationForm();
    }

    public static EducationFormBuilder anEducationForm() {
        return new EducationFormBuilder();
    }

    public EducationFormBuilder id(Long id) {
        educationForm.setId(id);
        return this;
    }

    public EducationFormBuilder educFormName(String educFormName) {
        educationForm.setEducFormName(educFormName);
        return this;
    }

    public EducationForm build() {
        return educationForm;
    }
}
