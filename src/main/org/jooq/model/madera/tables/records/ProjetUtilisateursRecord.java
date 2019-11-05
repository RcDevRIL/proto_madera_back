/*
 * This file is generated by jOOQ.
 */
package org.jooq.model.madera.tables.records;


import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;
import org.jooq.model.madera.tables.ProjetUtilisateurs;


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
public class ProjetUtilisateursRecord extends TableRecordImpl<ProjetUtilisateursRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 1899629663;

    /**
     * Setter for <code>madera.projet_utilisateurs.i_utilisateur_id</code>.
     */
    public void setIUtilisateurId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>madera.projet_utilisateurs.i_utilisateur_id</code>.
     */
    public Integer getIUtilisateurId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>madera.projet_utilisateurs.i_projet_id</code>.
     */
    public void setIProjetId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>madera.projet_utilisateurs.i_projet_id</code>.
     */
    public Integer getIProjetId() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ProjetUtilisateurs.PROJET_UTILISATEURS.I_UTILISATEUR_ID;
    }

    @Override
    public Field<Integer> field2() {
        return ProjetUtilisateurs.PROJET_UTILISATEURS.I_PROJET_ID;
    }

    @Override
    public Integer component1() {
        return getIUtilisateurId();
    }

    @Override
    public Integer component2() {
        return getIProjetId();
    }

    @Override
    public Integer value1() {
        return getIUtilisateurId();
    }

    @Override
    public Integer value2() {
        return getIProjetId();
    }

    @Override
    public ProjetUtilisateursRecord value1(Integer value) {
        setIUtilisateurId(value);
        return this;
    }

    @Override
    public ProjetUtilisateursRecord value2(Integer value) {
        setIProjetId(value);
        return this;
    }

    @Override
    public ProjetUtilisateursRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProjetUtilisateursRecord
     */
    public ProjetUtilisateursRecord() {
        super(ProjetUtilisateurs.PROJET_UTILISATEURS);
    }

    /**
     * Create a detached, initialised ProjetUtilisateursRecord
     */
    public ProjetUtilisateursRecord(Integer iUtilisateurId, Integer iProjetId) {
        super(ProjetUtilisateurs.PROJET_UTILISATEURS);

        set(0, iUtilisateurId);
        set(1, iProjetId);
    }
}
