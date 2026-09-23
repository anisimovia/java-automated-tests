package com.aqa.course.data;

public class PracticeFormData {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String gender;          // Male / Female / Other
    private final String mobile;
    private final String dateOfBirth;     // например "10 May 1990"
    private final String subject;         // например "Maths"
    private final String hobby;           // Sports / Reading / Music
    private final String picturePath;     // путь к файлу на диске
    private final String address;
    private final String state;           // например "NCR"
    private final String city;            // например "Delhi"

    public PracticeFormData(String firstName, String lastName, String email, String gender,
                            String mobile, String dateOfBirth, String subject, String hobby,
                            String picturePath, String address, String state, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.mobile = mobile;
        this.dateOfBirth = dateOfBirth;
        this.subject = subject;
        this.hobby = hobby;
        this.picturePath = picturePath;
        this.address = address;
        this.state = state;
        this.city = city;
    }

    public String getFirstName()      { return firstName; }
    public String getLastName()       { return lastName; }
    public String getEmail()          { return email; }
    public String getGender()         { return gender; }
    public String getMobile()         { return mobile; }
    public String getDateOfBirth()    { return dateOfBirth; }
    public String getSubject()        { return subject; }
    public String getHobby()          { return hobby; }
    public String getPicturePath()    { return picturePath; }
    public String getAddress()        { return address; }
    public String getState()          { return state; }
    public String getCity()           { return city; }
    public String getFullName()       { return firstName + " " + lastName; }
}