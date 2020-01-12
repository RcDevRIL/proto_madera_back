package com.madera.api.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.beans.ConstructorProperties;

/**
 * DTO Gamme
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 1.0-PRE-RELEASE
 */
@JsonSerialize
public class Gamme {

    public int gammeId;

    public String libelleGammes;

    @ConstructorProperties({ "i_gammes_id", "v_libelle_gammes" })
    public Gamme(int gammeId, String libelleGammes) {
        this.gammeId = gammeId;
        this.libelleGammes = libelleGammes;
    }

    public Gamme() {
    }

    public int getGammeId() {
        return gammeId;
    }

    public void setGammeId(int gammeId) {
        this.gammeId = gammeId;
    }

    public String getLibelleGammes() {
        return libelleGammes;
    }

    public void setLibelleGammes(String libelleGammes) {
        this.libelleGammes = libelleGammes;
    }
}
