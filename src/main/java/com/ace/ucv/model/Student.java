package com.ace.ucv.model;

public class Student {

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String genre;

    public Student() {
        this.id = 0;
        this.name = "";
        this.genre = "";
        this.surname = "";
        this.phone = "";
    }

    public Student(int id, String name, String surname, String phone, String genre) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.genre = genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (isValidPhoneNumber(phone)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Validates a Romanian phone number.
     *
     * @param phoneNumber The phone number to validate.
     * @return true if the phone number is valid, false otherwise.
     */
    public boolean isValidPhoneNumber(String phoneNumber) {
        // Regex pattern for Romanian phone numbers, allowing optional separators.
        String regex = "^(\\+?40|0)7[2-8]\\d(?:[.-]?\\d{2}){3}$";

        // Check if the phone number matches the pattern.
        return phoneNumber.matches(regex);
    }

}
