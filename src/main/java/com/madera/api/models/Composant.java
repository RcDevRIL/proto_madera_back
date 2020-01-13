package com.madera.api.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Optional;

/**
 * DTO Composant
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 1.0-RELEASE
 */
@JsonSerialize
public class Composant {

    public int composantId;

    Integer composantGroupeId;

    String libelleGroupe;

    public String libelle;

    public Integer composantReferentielId;

    public String caracteristiqueReferentiel;

    public String uniteUsage;

    public Optional<Double> section;

    public double prixComposant;

    public Composant() {
    }

    public int getComposantId() {
        return composantId;
    }

    public void setComposantId(int composantId) {
        this.composantId = composantId;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getComposantReferentielId() {
        return composantReferentielId;
    }

    public void setComposantReferentielId(Integer composantReferentielId) {
        this.composantReferentielId = composantReferentielId;
    }

    public String getCaracteristiqueReferentiel() {
        return caracteristiqueReferentiel;
    }

    public void setCaracteristiqueReferentiel(String caracteristiqueReferentiel) {
        this.caracteristiqueReferentiel = caracteristiqueReferentiel;
    }

    public String getUniteUsage() {
        return uniteUsage;
    }

    public void setUniteUsage(String uniteUsage) {
        this.uniteUsage = uniteUsage;
    }

    public Optional<Double> getSection() {
        return section;
    }

    public void setSection(Optional<Double> section) {
        this.section = section;
    }

    public double getPrixComposant() {
        return prixComposant;
    }

    public void setPrixComposant(double prixComposant) {
        this.prixComposant = prixComposant;
    }
}
