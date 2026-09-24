package com.aqa.course.data;

public class Generator {
    RandomData randomData;

    public Generator() {
        this.randomData = new RandomData();
    }


    public TextBoxData getTextBoxData() {
        return new TextBoxData(
                randomData.getFullName(),
                randomData.getEmail(),
                randomData.getAddress(),
                randomData.getAddress()
        );
    }

    public PracticeFormData getPracticeFormData() {
        return new PracticeFormData(
                randomData.getFirstName(),
                randomData.getLastName(),
                randomData.getEmail(),
                "Male",
                randomData.getMobile(),
                randomData.getDateOfBirth(),
                "Maths",
                "Sports",
                RandomData.createTestPicture(),
                randomData.getAddress(),
                "NCR",
                "Delhi"
        );
    }
}