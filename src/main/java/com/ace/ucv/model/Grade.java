package com.ace.ucv.model;

public class Grade {

    private int grade;
    private int subjectId;
    private int studentId;

    public Grade() {
        this.grade = 0;
        this.studentId = 0;
        this.subjectId = 0;
    }

    public Grade(int grade, int subjectId, int studentId) {
        this.grade = grade;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public int getGrade() {
        return grade;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
