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
            this.phone = phone;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
