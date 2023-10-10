package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Situatie {
    private Student student;
    private List<Nota> note;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Nota> getNote() {
        return note;
    }

    public void setNote(List<Nota> note) {
        this.note = note;
    }

    public void addNota(Nota nota) {
        if (note == null) {
            note = new ArrayList<Nota>();
        }
        note.add(nota);
    }
}
