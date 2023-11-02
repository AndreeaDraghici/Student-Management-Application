package com.ace.ucv.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.List;

public class Situation {
    private Student student;
    private List<Grade> grades;

    @SuppressFBWarnings("EI_EXPOSE_REP")
    public Student getStudent() {
        return student;
    }

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public void setStudent(Student student) {
        this.student = student;
    }

    @SuppressFBWarnings("EI_EXPOSE_REP")
    public List<Grade> getGrades() {
        if (grades == null) {
            grades = new ArrayList<>();
        }
        return grades;
    }

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void addGrade(Grade grade) {
        if (grades == null) {
            grades = new ArrayList<>();
        }
        grades.add(grade);
    }
}
