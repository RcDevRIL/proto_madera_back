package com.madera.api.models;

public class Produit {

    Integer produitId;

    String produitNom;

    Integer gammesId;

    double prixProduit;

    Boolean isModele;

    public Integer getProduitId() {
        return produitId;
    }

    public void setProduitId(Integer produitId) {
        this.produitId = produitId;
    }

    public String getProduitNom() {
        return produitNom;
    }

    public void setProduitNom(String produitNom) {
        this.produitNom = produitNom;
    }

    public Integer getGammesId() {
        return gammesId;
    }

    public void setGammesId(Integer gammesId) {
        this.gammesId = gammesId;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public Boolean getModele() {
        return isModele;
    }

    public void setModele(Boolean modele) {
        isModele = modele;
    }
}
