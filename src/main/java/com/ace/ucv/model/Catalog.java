package com.ace.ucv.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Discipline> materii;
    private List<Situation> situatii;

    public List<Discipline> getMaterii() {
        return materii;
    }

    public void setMaterii(List<Discipline> materii) {
        this.materii = materii;
    }

    public List<Situation> getSituatii() {
        return situatii;
    }

    public void setSituatii(List<Situation> situatii) {
        this.situatii = situatii;
    }

    public void addMaterie(Discipline discipline) {
        if (materii == null) {
            materii = new ArrayList<Discipline>();
        }
        materii.add(discipline);
    }

    public void addSituatie(Situation situation) {
        if (situatii == null) {
            situatii = new ArrayList<Situation>();
        }
        situatii.add(situation);
    }
}
