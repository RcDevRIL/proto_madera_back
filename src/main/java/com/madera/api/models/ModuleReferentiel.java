package com.madera.api.models;

//TODO Refactoring en referentiel (même classe que composantReferentiel)
public class ModuleReferentiel {

    Integer moduleReferentielId;

    String caracteristiqueReferentiel;

    String uniteUsage;

    public ModuleReferentiel() {
    }

    public ModuleReferentiel(Integer moduleReferentielId, String caracteristiqueReferentiel, String uniteUsage) {
        this.moduleReferentielId = moduleReferentielId;
        this.caracteristiqueReferentiel = caracteristiqueReferentiel;
        this.uniteUsage = uniteUsage;
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
}
