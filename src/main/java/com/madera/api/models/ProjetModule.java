package com.madera.api.models;

public class ProjetModule {

    Integer projetModuleId;

    Integer projetId;

    Integer moduleId;

    public ProjetModule() {
    }

    public Integer getProjetModuleId() {
        return projetModuleId;
    }

    public void setProjetModuleId(Integer projetModuleId) {
        this.projetModuleId = projetModuleId;
    }

    public Integer getProjetId() {
        return projetId;
    }

    public void setProjetId(Integer projetId) {
        this.projetId = projetId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}
