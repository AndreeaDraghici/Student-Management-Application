package com.ace.ucv.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Discipline> disciplines;
    private List<Situation> situations;

    @SuppressFBWarnings("EI_EXPOSE_REP")
    public List<Discipline> getDisciplines() {
        if (disciplines == null) {
            disciplines = new ArrayList<>();
        }
        return disciplines;
    }

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @SuppressFBWarnings("EI_EXPOSE_REP")
    public List<Situation> getSituations() {
        if (situations == null) {
            situations = new ArrayList<>();
        }
        return situations;
    }

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public void setSituations(List<Situation> situations) {
        this.situations = situations;
    }

    public void addDiscipline(Discipline discipline) {
        if (discipline == null) {
            return;
        }

        if (disciplines == null) {
            disciplines = new ArrayList<>();
        }
        disciplines.add(discipline);
    }

    @SuppressFBWarnings("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE")
    public void addSituation(Situation situation) {

        if (situations == null) {
            return;
        }

        if (situations == null) {
            situations = new ArrayList<>();
        }
        situations.add(situation);
    }
}
