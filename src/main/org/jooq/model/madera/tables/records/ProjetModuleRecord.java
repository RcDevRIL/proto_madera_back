/*
 * This file is generated by jOOQ.
 */
package org.jooq.model.madera.tables.records;


import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.model.madera.tables.ProjetModule;


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
public class ProjetModuleRecord extends UpdatableRecordImpl<ProjetModuleRecord> implements Record3<Integer, Integer, Integer> {

    private static final long serialVersionUID = -382104138;

    /**
     * Setter for <code>madera.projet_module.i_projet_module_id</code>.
     */
    public void setIProjetModuleId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>madera.projet_module.i_projet_module_id</code>.
     */
    public Integer getIProjetModuleId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>madera.projet_module.i_projet_id</code>.
     */
    public void setIProjetId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>madera.projet_module.i_projet_id</code>.
     */
    public Integer getIProjetId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>madera.projet_module.i_module_id</code>.
     */
    public void setIModuleId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>madera.projet_module.i_module_id</code>.
     */
    public Integer getIModuleId() {
        return (Integer) get(2);
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
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, Integer, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ProjetModule.PROJET_MODULE.I_PROJET_MODULE_ID;
    }

    @Override
    public Field<Integer> field2() {
        return ProjetModule.PROJET_MODULE.I_PROJET_ID;
    }

    @Override
    public Field<Integer> field3() {
        return ProjetModule.PROJET_MODULE.I_MODULE_ID;
    }

    @Override
    public Integer component1() {
        return getIProjetModuleId();
    }

    @Override
    public Integer component2() {
        return getIProjetId();
    }

    @Override
    public Integer component3() {
        return getIModuleId();
    }

    @Override
    public Integer value1() {
        return getIProjetModuleId();
    }

    @Override
    public Integer value2() {
        return getIProjetId();
    }

    @Override
    public Integer value3() {
        return getIModuleId();
    }

    @Override
    public ProjetModuleRecord value1(Integer value) {
        setIProjetModuleId(value);
        return this;
    }

    @Override
    public ProjetModuleRecord value2(Integer value) {
        setIProjetId(value);
        return this;
    }

    @Override
    public ProjetModuleRecord value3(Integer value) {
        setIModuleId(value);
        return this;
    }

    @Override
    public ProjetModuleRecord values(Integer value1, Integer value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProjetModuleRecord
     */
    public ProjetModuleRecord() {
        super(ProjetModule.PROJET_MODULE);
    }

    /**
     * Create a detached, initialised ProjetModuleRecord
     */
    public ProjetModuleRecord(Integer iProjetModuleId, Integer iProjetId, Integer iModuleId) {
        super(ProjetModule.PROJET_MODULE);

        set(0, iProjetModuleId);
        set(1, iProjetId);
        set(2, iModuleId);
    }
}
