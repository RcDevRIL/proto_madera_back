package com.madera.api.models;

public class ProduitModule {

    Integer produitModuleId;

    Integer produitId;

    Integer moduleId;

    String produitModuleNom;

    String produitModuleAngle;

    String produitModuleSectionLongueur;

    double prixModule;

    public ProduitModule() {
    }

    public ProduitModule(Integer moduleId, String produitModuleNom, String produitModuleAngle, String produitModuleSectionLongueur, double prixModule) {
        this.moduleId = moduleId;
        this.produitModuleNom = produitModuleNom;
        this.produitModuleAngle = produitModuleAngle;
        this.produitModuleSectionLongueur = produitModuleSectionLongueur;
        this.prixModule = prixModule;
    }

    public Integer getProduitModuleId() {
        return produitModuleId;
    }

    public void setProduitModuleId(Integer produitModuleId) {
        this.produitModuleId = produitModuleId;
    }

    public Integer getProduitId() {
        return produitId;
    }

    public void setProduitId(Integer produitId) {
        this.produitId = produitId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getProduitModuleNom() {
        return produitModuleNom;
    }

    public void setProduitModuleNom(String produitModuleNom) {
        this.produitModuleNom = produitModuleNom;
    }

    public String getProduitModuleAngle() {
        return produitModuleAngle;
    }

    public void setProduitModuleAngle(String produitModuleAngle) {
        this.produitModuleAngle = produitModuleAngle;
    }

    public String getProduitModuleSectionLongueur() {
        return produitModuleSectionLongueur;
    }

    public void setProduitModuleSectionLongueur(String getProduitModuleSectionLongueur) {
        this.produitModuleSectionLongueur = getProduitModuleSectionLongueur;
    }

    public double getPrixModule() {
        return prixModule;
    }

    public void setPrixModule(double prixModule) {
        this.prixModule = prixModule;
    }
}
