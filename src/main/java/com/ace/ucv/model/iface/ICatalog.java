package com.ace.ucv.model.iface;

import com.ace.ucv.model.Discipline;
import com.ace.ucv.model.Grade;
import com.ace.ucv.model.Student;

/**
 * Created by Andreea Draghici on 11/12/2023
 * Name of project: StudentManagement
 */
public interface ICatalog {
    void addStudent(Student student);
    void addDiscipline(Discipline discipline);
    void addGrade(Grade grade);
}
