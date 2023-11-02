package com.ace.ucv.model;

public class Student {

    private final int id;
    private String name;
    private String surname;
    private String phone;
    private String genre;

    public Student(int id) {
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

    public java.lang.String getPhone() {
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

    public boolean isValidPhoneNumber(String phoneNumber) {
        // Add phone number validation logic here
        String regex = "^(\\+?40|0)7[2-8]\\d{1}(?:[.-]?\\d{2}){3}$";

        return phoneNumber.matches(regex);
    }
}
