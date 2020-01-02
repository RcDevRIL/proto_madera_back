package com.madera.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Date;

/**
 * DTO Projet
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.2-PRE-RELEASE
 */
@JsonSerialize
public class Projet {

    public Integer projetId;

    public String nomProjet;

    public String refProjet;

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date dateProjet;

    public double prixTotal;

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

    public Integer getProjetId() {
        return projetId;
    }

    public void setProjetId(Integer projetId) {
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

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
}
