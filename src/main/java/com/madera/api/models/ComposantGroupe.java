package com.madera.api.models;

public class ComposantGroupe {

    Integer composantGroupeId;

    String libelleGroupe;

    public ComposantGroupe(Integer composantGroupeId, String libelleGroupe) {
        this.composantGroupeId = composantGroupeId;
        this.libelleGroupe = libelleGroupe;
    }

    public ComposantGroupe() {
    }

    public Integer getComposantGroupeId() {
        return composantGroupeId;
    }

    public void setComposantGroupeId(Integer composantGroupeId) {
        this.composantGroupeId = composantGroupeId;
    }

    public String getLibelleGroupe() {
        return libelleGroupe;
    }

    public void setLibelleGroupe(String libelleGroupe) {
        this.libelleGroupe = libelleGroupe;
    }
}
