package com.madera.api.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.beans.ConstructorProperties;

/**
 * DTO Composant
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-SNAPSHOT
 */
@JsonSerialize
public class Composant {

    // TODO A voir si on garde les champs tels que composantGroupeId, ou si l'on
    // renvoi également le libelle du composantGroupe
    // TODO C'est un exemple
    public int composantId;

    public Integer composantGroupeId;

    public String libelle;

    public Integer composantReferentielId;

    public double section;

    @ConstructorProperties({ "i_composant_id", "i_composant_groupe_id", "v_libelle", "i_composant_referentielId",
            "f_section" })
    public Composant(int composantId, Integer composantGroupeId, String libelle, Integer composantReferentielId,
            double section) {
        this.composantId = composantId;
        this.composantGroupeId = composantGroupeId;
        this.libelle = libelle;
        this.composantReferentielId = composantReferentielId;
        this.section = section;
    }
}
