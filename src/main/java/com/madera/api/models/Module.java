package com.madera.api.models;

import java.util.List;

public class Module {

    Integer moduleId;

    Integer gammeId;

    ModuleReferentiel moduleReferentiel;

    //TODO rajouter la liste des composants

    String nom;

    String angle;

    String natureModule;

    Boolean modele;

    public Module() {
    }

    public Module(Integer moduleId, Integer gammeId, ModuleReferentiel moduleReferentiel, String nom, String angle, String natureModule, Boolean modele) {
        this.moduleId = moduleId;
        this.gammeId = gammeId;
        this.moduleReferentiel = moduleReferentiel;
        this.nom = nom;
        this.angle = angle;
        this.natureModule = natureModule;
        this.modele = modele;
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

    public ModuleReferentiel getModuleReferentiel() {
        return moduleReferentiel;
    }

    public void setModuleReferentiel(ModuleReferentiel moduleReferentiel) {
        this.moduleReferentiel = moduleReferentiel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getNatureModule() {
        return natureModule;
    }

    public void setNatureModule(String natureModule) {
        this.natureModule = natureModule;
    }

    public Boolean getModele() {
        return modele;
    }

    public void setModele(Boolean modele) {
        this.modele = modele;
    }
}
