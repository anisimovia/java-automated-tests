package com.aqa.course.data;

public class TextBoxData {
    private final String fullName;
    private final String email;
    private final String currentAddress;
    private final String permanentAddress;

    public TextBoxData(String fullName, String email, String currentAddress, String permanentAddress) {
        this.fullName = fullName;
        this.email = email;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getCurrentAddress() { return currentAddress; }
    public String getPermanentAddress() { return permanentAddress; }
}