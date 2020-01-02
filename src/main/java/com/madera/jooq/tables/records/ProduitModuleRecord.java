/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables.records;


import com.madera.jooq.tables.ProduitModule;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.JSONB;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class ProduitModuleRecord extends UpdatableRecordImpl<ProduitModuleRecord> implements Record6<Integer, Integer, Integer, String, String, JSONB> {

    private static final long serialVersionUID = 1248462515;

    /**
     * Setter for <code>madera.produit_module.i_produit_module_id</code>.
     */
    public void setIProduitModuleId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>madera.produit_module.i_produit_module_id</code>.
     */
    public Integer getIProduitModuleId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>madera.produit_module.i_produit_id</code>.
     */
    public void setIProduitId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>madera.produit_module.i_produit_id</code>.
     */
    public Integer getIProduitId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>madera.produit_module.i_module_id</code>.
     */
    public void setIModuleId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>madera.produit_module.i_module_id</code>.
     */
    public Integer getIModuleId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>madera.produit_module.v_produit_module_nom</code>.
     */
    public void setVProduitModuleNom(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>madera.produit_module.v_produit_module_nom</code>.
     */
    public String getVProduitModuleNom() {
        return (String) get(3);
    }

    /**
     * Setter for <code>madera.produit_module.v_produit_module_angle</code>.
     */
    public void setVProduitModuleAngle(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>madera.produit_module.v_produit_module_angle</code>.
     */
    public String getVProduitModuleAngle() {
        return (String) get(4);
    }

    /**
     * Setter for <code>madera.produit_module.j_section_longueur</code>.
     */
    public void setJSectionLongueur(JSONB value) {
        set(5, value);
    }

    /**
     * Getter for <code>madera.produit_module.j_section_longueur</code>.
     */
    public JSONB getJSectionLongueur() {
        return (JSONB) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, Integer, String, String, JSONB> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, Integer, Integer, String, String, JSONB> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ProduitModule.PRODUIT_MODULE.I_PRODUIT_MODULE_ID;
    }

    @Override
    public Field<Integer> field2() {
        return ProduitModule.PRODUIT_MODULE.I_PRODUIT_ID;
    }

    @Override
    public Field<Integer> field3() {
        return ProduitModule.PRODUIT_MODULE.I_MODULE_ID;
    }

    @Override
    public Field<String> field4() {
        return ProduitModule.PRODUIT_MODULE.V_PRODUIT_MODULE_NOM;
    }

    @Override
    public Field<String> field5() {
        return ProduitModule.PRODUIT_MODULE.V_PRODUIT_MODULE_ANGLE;
    }

    @Override
    public Field<JSONB> field6() {
        return ProduitModule.PRODUIT_MODULE.J_SECTION_LONGUEUR;
    }

    @Override
    public Integer component1() {
        return getIProduitModuleId();
    }

    @Override
    public Integer component2() {
        return getIProduitId();
    }

    @Override
    public Integer component3() {
        return getIModuleId();
    }

    @Override
    public String component4() {
        return getVProduitModuleNom();
    }

    @Override
    public String component5() {
        return getVProduitModuleAngle();
    }

    @Override
    public JSONB component6() {
        return getJSectionLongueur();
    }

    @Override
    public Integer value1() {
        return getIProduitModuleId();
    }

    @Override
    public Integer value2() {
        return getIProduitId();
    }

    @Override
    public Integer value3() {
        return getIModuleId();
    }

    @Override
    public String value4() {
        return getVProduitModuleNom();
    }

    @Override
    public String value5() {
        return getVProduitModuleAngle();
    }

    @Override
    public JSONB value6() {
        return getJSectionLongueur();
    }

    @Override
    public ProduitModuleRecord value1(Integer value) {
        setIProduitModuleId(value);
        return this;
    }

    @Override
    public ProduitModuleRecord value2(Integer value) {
        setIProduitId(value);
        return this;
    }

    @Override
    public ProduitModuleRecord value3(Integer value) {
        setIModuleId(value);
        return this;
    }

    @Override
    public ProduitModuleRecord value4(String value) {
        setVProduitModuleNom(value);
        return this;
    }

    @Override
    public ProduitModuleRecord value5(String value) {
        setVProduitModuleAngle(value);
        return this;
    }

    @Override
    public ProduitModuleRecord value6(JSONB value) {
        setJSectionLongueur(value);
        return this;
    }

    @Override
    public ProduitModuleRecord values(Integer value1, Integer value2, Integer value3, String value4, String value5, JSONB value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProduitModuleRecord
     */
    public ProduitModuleRecord() {
        super(ProduitModule.PRODUIT_MODULE);
    }

    /**
     * Create a detached, initialised ProduitModuleRecord
     */
    public ProduitModuleRecord(Integer iProduitModuleId, Integer iProduitId, Integer iModuleId, String vProduitModuleNom, String vProduitModuleAngle, JSONB jSectionLongueur) {
        super(ProduitModule.PRODUIT_MODULE);

        set(0, iProduitModuleId);
        set(1, iProduitId);
        set(2, iModuleId);
        set(3, vProduitModuleNom);
        set(4, vProduitModuleAngle);
        set(5, jSectionLongueur);
    }
}