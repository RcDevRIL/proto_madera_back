package com.madera.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * DTO Projet
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-SNAPSHOT
 */
@JsonSerialize
public class Projet {

    public int projetId;

    public String nomProjet;

    public String refProjet;

    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date dateProjet;

    public double prix;

    public Client clientProjet;

    public DevisEtat devisEtat;

    public int getProjetId() {
        return projetId;
    }

    public void setProjetId(int projetId) {
        this.projetId = projetId;
    }

    public Client getClientProjet() {
        return clientProjet;
    }

    public void setClientProjet(Client clientProjet) {
        this.clientProjet = clientProjet;
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

    public DevisEtat getDevisEtat() {
        return devisEtat;
    }

    public void setDevisEtat(DevisEtat devisEtat) {
        this.devisEtat = devisEtat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
