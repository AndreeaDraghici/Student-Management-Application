package com.ace.ucv.model;

public class Grade {

    private final int grade;
    private final int subjectId;
    private final int studentId;

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


}
