package com.madera.api.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.beans.ConstructorProperties;

/**
 * DTO DevisEtat
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.4-RELEASE
 */
@JsonSerialize
public class DevisEtat {

    public Integer devisEtatId;

    public String devisEtatLibelle;

    public Integer pourcentageSomme;

    public DevisEtat() {
    }

    @ConstructorProperties({ "i_devis_etat_id", "v_devis_etat_libelle", "i_pourcentage_somme" })
    public DevisEtat(Integer devisEtatId, String devisEtatLibelle, Integer pourcentageSomme) {
        this.devisEtatId = devisEtatId;
        this.devisEtatLibelle = devisEtatLibelle;
        this.pourcentageSomme = pourcentageSomme;
    }

    public Integer getDevisEtatId() {
        return devisEtatId;
    }

    public void setDevisEtatId(Integer devisEtatId) {
        this.devisEtatId = devisEtatId;
    }

    public String getDevisEtatLibelle() {
        return devisEtatLibelle;
    }

    public void setDevisEtatLibelle(String devisEtatLibelle) {
        this.devisEtatLibelle = devisEtatLibelle;
    }

    public Integer getPourcentageSomme() {
        return pourcentageSomme;
    }

    public void setPourcentageSomme(Integer pourcentageSomme) {
        this.pourcentageSomme = pourcentageSomme;
    }

}
