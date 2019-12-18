package com.madera.api.models;

/**
 * DTO ProjetModule
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.2-PRE-RELEASE
 */
public class ProjetModule {

    Integer projetModuleId;

    Integer projetId;

    Integer moduleId;

    public ProjetModule() {
    }

    public ProjetModule(Integer moduleId) {
        this.moduleId = moduleId;
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
