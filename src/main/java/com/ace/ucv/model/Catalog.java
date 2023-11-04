package com.ace.ucv.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Student> students;
    private List<Discipline> disciplines;

    public Catalog() {
        this.disciplines = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public Catalog(List<Student> students, List<Discipline> disciplines) {
        this.students = students;
        this.disciplines = disciplines;
    }

    public List<Student> getStudents() {
        if (students == null) {
            students = new ArrayList<>();
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Discipline> getDisciplines() {
        if (disciplines == null) {
            disciplines = new ArrayList<>();
        }

        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

}
