package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Materie> materii;
    private List<Situatie> situatii;

    public List<Materie> getMaterii() {
        return materii;
    }

    public void setMaterii(List<Materie> materii) {
        this.materii = materii;
    }

    public List<Situatie> getSituatii() {
        return situatii;
    }

    public void setSituatii(List<Situatie> situatii) {
        this.situatii = situatii;
    }

    public void addMaterie(Materie materie) {
        if (materii == null) {
            materii = new ArrayList<Materie>();
        }
        materii.add(materie);
    }

    public void addSituatie(Situatie situatie) {
        if (situatii == null) {
            situatii = new ArrayList<Situatie>();
        }
        situatii.add(situatie);
    }
}
