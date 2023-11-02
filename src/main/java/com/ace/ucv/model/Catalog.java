package com.ace.ucv.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Discipline> disciplines;
    private List<Situation> situations;

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public List<Situation> getSituations() {
        return situations;
    }

    public void setSituations(List<Situation> situations) {
        this.situations = situations;
    }

    public void addDiscipline(Discipline discipline) {
        if (disciplines == null) {
            disciplines = new ArrayList<>();
        }
        disciplines.add(discipline);
    }

    public void addSituation(Situation situation) {
        if (situations == null) {
            situations = new ArrayList<>();
        }
        situations.add(situation);
    }
}
