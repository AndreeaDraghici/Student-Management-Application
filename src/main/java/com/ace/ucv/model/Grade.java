package com.ace.ucv.model;

public class Grade {

    private final float grade;
    private final int subjectId;
    private final int studentId;

    public Grade(float grade, int subjectId, int studentId) {
        this.grade = grade;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public float getGrade() {
        return grade;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getStudentId() {
        return studentId;
    }
}
