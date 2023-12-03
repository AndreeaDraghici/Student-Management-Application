package com.ace.ucv.model;

public class Grade {

    private int gradeValue;
    private int subjectId;
    private int studentId;

    public Grade() {
        this.gradeValue = 0;
        this.studentId = 0;
        this.subjectId = 0;
    }

    public Grade(int gradeValue, int subjectId, int studentId) {
        this.gradeValue = gradeValue;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
