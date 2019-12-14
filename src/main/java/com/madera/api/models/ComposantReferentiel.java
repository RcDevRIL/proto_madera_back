package com.madera.api.models;

/**
 * DTO ComposantReferentiel
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.2-PRE-RELEASE
 */
public class ComposantReferentiel {

    public Integer composantReferentielId;

    public String caracteristiqueReferentiel;

    public String uniteUsage;

    public ComposantReferentiel() {
    }

    public ComposantReferentiel(Integer composantReferentielId, String caracteristiqueReferentiel, String uniteUsage) {
        this.composantReferentielId = composantReferentielId;
        this.caracteristiqueReferentiel = caracteristiqueReferentiel;
        this.uniteUsage = uniteUsage;
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
}
