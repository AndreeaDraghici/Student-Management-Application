package com.ace.ucv.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog implements ICatalog{

    private List<Student> students;
    private List<Discipline> disciplines;
    private List<Grade> grades;

    public Catalog() {
        this.disciplines = new ArrayList<>();
        this.students = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    public Catalog(List<Student> students, List<Discipline> disciplines, List<Grade> grades) {
        this.students = students;
        this.disciplines = disciplines;
        this.grades = grades;
    }

    public List<Student> getStudents() {
        if (students == null) {
            students = new ArrayList<>();
        }
        return students;
    }

    @SuppressWarnings("unused") // Marking the method as used
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Discipline> getDisciplines() {
        if (disciplines == null) {
            disciplines = new ArrayList<>();
        }

        return disciplines;
    }

    @SuppressWarnings("unused") // Marking the method as used
    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }


    public List<Grade> getGrades() {
        if (grades == null) {
            grades = new ArrayList<>();
        }
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    /**
     * Adds a student to the catalog.
     *
     * @param student The student to be added.
     */
    @Override
    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    /**
     * Adds a discipline to the catalog.
     *
     * @param discipline The discipline to be added.
     */
    @Override
    public void addDiscipline(Discipline discipline) {
        if (disciplines == null) {
            disciplines = new ArrayList<>();
        }
        disciplines.add(discipline);
    }

    /**
     * Adds a grade to the catalog.
     *
     * @param grade The grade to be added.
     */
    @Override
    public void addGrade(Grade grade) {
        if (grades == null) {
            grades = new ArrayList<>();
        }

        grades.add(grade);
    }
}