package com.madera.api.models;

/**
 * DTO ComposantGroupe
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.4-RELEASE
 */
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
