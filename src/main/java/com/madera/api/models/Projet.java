package com.madera.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * DTO Projet
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-RELEASE
 */
@JsonSerialize
public class Projet {

    public int projetId;

    public String nomProjet;

    public String refProjet;

    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date dateProjet;

    public double prix;

    public Integer clientId;

    public Integer devisEtatId;

    public Projet() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getDevisEtatId() {
        return devisEtatId;
    }

    public void setDevisEtatId(Integer devisEtatId) {
        this.devisEtatId = devisEtatId;
    }

    public int getProjetId() {
        return projetId;
    }

    public void setProjetId(int projetId) {
        this.projetId = projetId;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getRefProjet() {
        return refProjet;
    }

    public void setRefProjet(String refProjet) {
        this.refProjet = refProjet;
    }

    public Date getDateProjet() {
        return dateProjet;
    }

    public void setDateProjet(Date dateProjet) {
        this.dateProjet = dateProjet;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
