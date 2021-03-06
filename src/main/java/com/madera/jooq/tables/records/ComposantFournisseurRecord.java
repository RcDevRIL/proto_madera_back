/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables.records;


import com.madera.jooq.tables.ComposantFournisseur;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record2;
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
public class ComposantFournisseurRecord extends UpdatableRecordImpl<ComposantFournisseurRecord> implements Record3<Integer, Integer, String> {

    private static final long serialVersionUID = 1175637927;

    /**
     * Setter for <code>madera.composant_fournisseur.i_composant_id</code>.
     */
    public void setIComposantId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>madera.composant_fournisseur.i_composant_id</code>.
     */
    public Integer getIComposantId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>madera.composant_fournisseur.i_fournisseur_id</code>.
     */
    public void setIFournisseurId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>madera.composant_fournisseur.i_fournisseur_id</code>.
     */
    public Integer getIFournisseurId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>madera.composant_fournisseur.v_ref_fournisseur</code>.
     */
    public void setVRefFournisseur(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>madera.composant_fournisseur.v_ref_fournisseur</code>.
     */
    public String getVRefFournisseur() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, Integer, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ComposantFournisseur.COMPOSANT_FOURNISSEUR.I_COMPOSANT_ID;
    }

    @Override
    public Field<Integer> field2() {
        return ComposantFournisseur.COMPOSANT_FOURNISSEUR.I_FOURNISSEUR_ID;
    }

    @Override
    public Field<String> field3() {
        return ComposantFournisseur.COMPOSANT_FOURNISSEUR.V_REF_FOURNISSEUR;
    }

    @Override
    public Integer component1() {
        return getIComposantId();
    }

    @Override
    public Integer component2() {
        return getIFournisseurId();
    }

    @Override
    public String component3() {
        return getVRefFournisseur();
    }

    @Override
    public Integer value1() {
        return getIComposantId();
    }

    @Override
    public Integer value2() {
        return getIFournisseurId();
    }

    @Override
    public String value3() {
        return getVRefFournisseur();
    }

    @Override
    public ComposantFournisseurRecord value1(Integer value) {
        setIComposantId(value);
        return this;
    }

    @Override
    public ComposantFournisseurRecord value2(Integer value) {
        setIFournisseurId(value);
        return this;
    }

    @Override
    public ComposantFournisseurRecord value3(String value) {
        setVRefFournisseur(value);
        return this;
    }

    @Override
    public ComposantFournisseurRecord values(Integer value1, Integer value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ComposantFournisseurRecord
     */
    public ComposantFournisseurRecord() {
        super(ComposantFournisseur.COMPOSANT_FOURNISSEUR);
    }

    /**
     * Create a detached, initialised ComposantFournisseurRecord
     */
    public ComposantFournisseurRecord(Integer iComposantId, Integer iFournisseurId, String vRefFournisseur) {
        super(ComposantFournisseur.COMPOSANT_FOURNISSEUR);

        set(0, iComposantId);
        set(1, iFournisseurId);
        set(2, vRefFournisseur);
    }
}
