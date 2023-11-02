package com.ace.ucv.model;

import java.util.ArrayList;
import java.util.List;

public class Situation {
    private Student student;
    private List<Grade> note;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Grade> getNote() {
        return note;
    }

    public void setNote(List<Grade> note) {
        this.note = note;
    }

    public void addNota(Grade grade) {
        if (note == null) {
            note = new ArrayList<>();
        }
        note.add(grade);
    }
}
