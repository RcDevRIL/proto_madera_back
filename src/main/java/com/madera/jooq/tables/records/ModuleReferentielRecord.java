/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables.records;


import com.madera.jooq.tables.ModuleReferentiel;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ModuleReferentielRecord extends UpdatableRecordImpl<ModuleReferentielRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = 2069000344;

    /**
     * Setter for <code>madera.module_referentiel.i_module_referentiel_id</code>.
     */
    public void setIModuleReferentielId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>madera.module_referentiel.i_module_referentiel_id</code>.
     */
    public Integer getIModuleReferentielId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>madera.module_referentiel.v_caracteristique</code>.
     */
    public void setVCaracteristique(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>madera.module_referentiel.v_caracteristique</code>.
     */
    public String getVCaracteristique() {
        return (String) get(1);
    }

    /**
     * Setter for <code>madera.module_referentiel.v_unite_usage</code>.
     */
    public void setVUniteUsage(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>madera.module_referentiel.v_unite_usage</code>.
     */
    public String getVUniteUsage() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ModuleReferentiel.MODULE_REFERENTIEL.I_MODULE_REFERENTIEL_ID;
    }

    @Override
    public Field<String> field2() {
        return ModuleReferentiel.MODULE_REFERENTIEL.V_CARACTERISTIQUE;
    }

    @Override
    public Field<String> field3() {
        return ModuleReferentiel.MODULE_REFERENTIEL.V_UNITE_USAGE;
    }

    @Override
    public Integer component1() {
        return getIModuleReferentielId();
    }

    @Override
    public String component2() {
        return getVCaracteristique();
    }

    @Override
    public String component3() {
        return getVUniteUsage();
    }

    @Override
    public Integer value1() {
        return getIModuleReferentielId();
    }

    @Override
    public String value2() {
        return getVCaracteristique();
    }

    @Override
    public String value3() {
        return getVUniteUsage();
    }

    @Override
    public ModuleReferentielRecord value1(Integer value) {
        setIModuleReferentielId(value);
        return this;
    }

    @Override
    public ModuleReferentielRecord value2(String value) {
        setVCaracteristique(value);
        return this;
    }

    @Override
    public ModuleReferentielRecord value3(String value) {
        setVUniteUsage(value);
        return this;
    }

    @Override
    public ModuleReferentielRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ModuleReferentielRecord
     */
    public ModuleReferentielRecord() {
        super(ModuleReferentiel.MODULE_REFERENTIEL);
    }

    /**
     * Create a detached, initialised ModuleReferentielRecord
     */
    public ModuleReferentielRecord(Integer iModuleReferentielId, String vCaracteristique, String vUniteUsage) {
        super(ModuleReferentiel.MODULE_REFERENTIEL);

        set(0, iModuleReferentielId);
        set(1, vCaracteristique);
        set(2, vUniteUsage);
    }
}