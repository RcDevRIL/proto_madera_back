/*
 * This file is generated by jOOQ.
 */
package org.jooq.model.madera.tables.records;


import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.model.madera.tables.Composant;


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
public class ComposantRecord extends UpdatableRecordImpl<ComposantRecord> implements Record5<Integer, Integer, String, Integer, Double> {

    private static final long serialVersionUID = -653134736;

    /**
     * Setter for <code>madera.composant.i_composant_id</code>.
     */
    public void setIComposantId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>madera.composant.i_composant_id</code>.
     */
    public Integer getIComposantId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>madera.composant.i_composant_groupe_id</code>.
     */
    public void setIComposantGroupeId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>madera.composant.i_composant_groupe_id</code>.
     */
    public Integer getIComposantGroupeId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>madera.composant.v_libelle</code>.
     */
    public void setVLibelle(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>madera.composant.v_libelle</code>.
     */
    public String getVLibelle() {
        return (String) get(2);
    }

    /**
     * Setter for <code>madera.composant.i_composant_referentiel_id</code>.
     */
    public void setIComposantReferentielId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>madera.composant.i_composant_referentiel_id</code>.
     */
    public Integer getIComposantReferentielId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>madera.composant.f_section</code>.
     */
    public void setFSection(Double value) {
        set(4, value);
    }

    /**
     * Getter for <code>madera.composant.f_section</code>.
     */
    public Double getFSection() {
        return (Double) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, String, Integer, Double> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, Integer, String, Integer, Double> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Composant.COMPOSANT.I_COMPOSANT_ID;
    }

    @Override
    public Field<Integer> field2() {
        return Composant.COMPOSANT.I_COMPOSANT_GROUPE_ID;
    }

    @Override
    public Field<String> field3() {
        return Composant.COMPOSANT.V_LIBELLE;
    }

    @Override
    public Field<Integer> field4() {
        return Composant.COMPOSANT.I_COMPOSANT_REFERENTIEL_ID;
    }

    @Override
    public Field<Double> field5() {
        return Composant.COMPOSANT.F_SECTION;
    }

    @Override
    public Integer component1() {
        return getIComposantId();
    }

    @Override
    public Integer component2() {
        return getIComposantGroupeId();
    }

    @Override
    public String component3() {
        return getVLibelle();
    }

    @Override
    public Integer component4() {
        return getIComposantReferentielId();
    }

    @Override
    public Double component5() {
        return getFSection();
    }

    @Override
    public Integer value1() {
        return getIComposantId();
    }

    @Override
    public Integer value2() {
        return getIComposantGroupeId();
    }

    @Override
    public String value3() {
        return getVLibelle();
    }

    @Override
    public Integer value4() {
        return getIComposantReferentielId();
    }

    @Override
    public Double value5() {
        return getFSection();
    }

    @Override
    public ComposantRecord value1(Integer value) {
        setIComposantId(value);
        return this;
    }

    @Override
    public ComposantRecord value2(Integer value) {
        setIComposantGroupeId(value);
        return this;
    }

    @Override
    public ComposantRecord value3(String value) {
        setVLibelle(value);
        return this;
    }

    @Override
    public ComposantRecord value4(Integer value) {
        setIComposantReferentielId(value);
        return this;
    }

    @Override
    public ComposantRecord value5(Double value) {
        setFSection(value);
        return this;
    }

    @Override
    public ComposantRecord values(Integer value1, Integer value2, String value3, Integer value4, Double value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ComposantRecord
     */
    public ComposantRecord() {
        super(Composant.COMPOSANT);
    }

    /**
     * Create a detached, initialised ComposantRecord
     */
    public ComposantRecord(Integer iComposantId, Integer iComposantGroupeId, String vLibelle, Integer iComposantReferentielId, Double fSection) {
        super(Composant.COMPOSANT);

        set(0, iComposantId);
        set(1, iComposantGroupeId);
        set(2, vLibelle);
        set(3, iComposantReferentielId);
        set(4, fSection);
    }
}
