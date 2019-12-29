package com.madera.api.models;

import java.util.List;

public class Module {

    Integer moduleId;

    Integer gammeId;

    Integer moduleReferentielId;

    String caracteristiqueReferentiel;

    String uniteUsage;

    String nom;

    String natureModule;

    public Module() {
    }

    public Module(Integer moduleId, Integer gammeId, Integer moduleReferentielId, String caracteristiqueReferentiel, String uniteUsage, String nom, String angle, String natureModule, Boolean modele) {
        this.moduleId = moduleId;
        this.gammeId = gammeId;
        this.moduleReferentielId = moduleReferentielId;
        this.caracteristiqueReferentiel = caracteristiqueReferentiel;
        this.uniteUsage = uniteUsage;
        this.nom = nom;
        this.natureModule = natureModule;
    }

    public Integer getModuleReferentielId() {
        return moduleReferentielId;
    }

    public void setModuleReferentielId(Integer moduleReferentielId) {
        this.moduleReferentielId = moduleReferentielId;
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

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getGammeId() {
        return gammeId;
    }

    public void setGammeId(Integer gammeId) {
        this.gammeId = gammeId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNatureModule() {
        return natureModule;
    }

    public void setNatureModule(String natureModule) {
        this.natureModule = natureModule;
    }
}
