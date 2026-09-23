package com.aqa.course.data;

import com.aqa.course.api.models.posts.Posts;

public class Generator {
    RandomData randomData;

    public Generator() {
        this.randomData = new RandomData();
    }

    public Posts getPosts() {
        return new Posts(
                randomData.getTitle(),
                randomData.getBody(),
                randomData.getID()
        );
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