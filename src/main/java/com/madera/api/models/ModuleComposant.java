package com.madera.api.models;

/**
 * DTO ModuleComposant
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.3-RELEASE
 */
public class ModuleComposant {

    Integer moduleId;

    Integer composantId;

    Integer ordre;

    public ModuleComposant() {
    }

    public ModuleComposant(Integer moduleId, Integer composantId, Integer ordre) {
        this.moduleId = moduleId;
        this.composantId = composantId;
        this.ordre = ordre;
    }

    public Integer getComposantId() {
        return composantId;
    }

    public void setComposantId(Integer composantId) {
        this.composantId = composantId;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}
