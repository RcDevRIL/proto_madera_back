package com.madera.api.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.beans.ConstructorProperties;

/**
 * DTO Gamme
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-SNAPSHOT
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
}
