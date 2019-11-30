package com.madera.api.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.lang.Nullable;

import java.beans.ConstructorProperties;
import java.util.Optional;

/**
 * DTO Composant
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-RELEASE
 */
@JsonSerialize
public class Composant {

    public int composantId;

    public ComposantGroupe composantGroupe;
    public String libelle;

    public ComposantReferentiel composantReferentiel;

    public Optional<Double> section;

    public Composant() {
    }

    public int getComposantId() {
        return composantId;
    }

    public void setComposantId(int composantId) {
        this.composantId = composantId;
    }

    public ComposantGroupe getComposantGroupe() {
        return composantGroupe;
    }

    public void setComposantGroupe(ComposantGroupe composantGroupel) {
        this.composantGroupe = composantGroupel;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ComposantReferentiel getComposantReferentiel() {
        return composantReferentiel;
    }

    public void setComposantReferentiel(ComposantReferentiel composantReferentiel) {
        this.composantReferentiel = composantReferentiel;
    }

    public Optional<Double> getSection() {
        return section;
    }

    public void setSection(Optional<Double> section) {
        this.section = section;
    }
}
