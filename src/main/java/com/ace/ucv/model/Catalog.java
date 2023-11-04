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

    /**
     * Adds a student to the catalog.
     *
     * @param student The student to be added.
     */
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
    public void addDiscipline(Discipline discipline) {
        if (disciplines == null) {
            disciplines = new ArrayList<>();
        }
        disciplines.add(discipline);
    }

    /**
     * Removes a student from the catalog.
     *
     * @param student The student to be removed.
     */
    @SuppressWarnings("unused") // Marking the method as used
    public void removeStudent(Student student) {
        if (students != null) {
            students.remove(student);
        }
    }

    /**
     * Removes a discipline from the catalog.
     *
     * @param discipline The discipline to be removed.
     */
    @SuppressWarnings("unused") // Marking the method as used
    public void removeDiscipline(Discipline discipline) {
        if (disciplines != null) {
            disciplines.remove(discipline);
        }
    }
}
