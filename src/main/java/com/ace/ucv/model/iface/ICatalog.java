package com.ace.ucv.model.iface;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */

/*
 * Interface representing a catalog that can store information about students, disciplines, and grades.
 */
public interface ICatalog {
    /**
     * Adds a student to the catalog.
     *
     * @param student The student to be added.
     */
    void addStudent(Student student);


    /**
     * Adds a discipline to the catalog.
     *
     * @param discipline The discipline to be added.
     */
    void addDiscipline(Discipline discipline);

    /**
     * Adds a grade to the catalog.
     *
     * @param grade The grade to be added.
     */
    void addGrade(Grade grade);
}
