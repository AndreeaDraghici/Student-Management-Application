
package com.ace.ucv.model;

public class Discipline {

    private int id;
    private String name;
    private String teacher;
    private int semester;
    private String year;


    public Discipline() {
        this.id = 0;
        this.name = "";
        this.teacher = "";
        this.semester = 0;
        this.year = "";
    }

    public Discipline(int id, String name, String teacher, int semester, String year) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.semester = semester;
        this.year = year;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setId(int id) {
        this.id = id;
    }
}
