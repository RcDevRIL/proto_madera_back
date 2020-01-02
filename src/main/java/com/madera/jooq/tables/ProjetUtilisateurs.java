/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables;


import com.madera.jooq.Indexes;
import com.madera.jooq.Keys;
import com.madera.jooq.Madera;
import com.madera.jooq.tables.records.ProjetUtilisateursRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class ProjetUtilisateurs extends TableImpl<ProjetUtilisateursRecord> {

    private static final long serialVersionUID = 1980177873;

    /**
     * The reference instance of <code>madera.projet_utilisateurs</code>
     */
    public static final ProjetUtilisateurs PROJET_UTILISATEURS = new ProjetUtilisateurs();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProjetUtilisateursRecord> getRecordType() {
        return ProjetUtilisateursRecord.class;
    }

    /**
     * The column <code>madera.projet_utilisateurs.i_utilisateur_id</code>.
     */
    public final TableField<ProjetUtilisateursRecord, Integer> I_UTILISATEUR_ID = createField(DSL.name("i_utilisateur_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>madera.projet_utilisateurs.i_projet_id</code>.
     */
    public final TableField<ProjetUtilisateursRecord, Integer> I_PROJET_ID = createField(DSL.name("i_projet_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>madera.projet_utilisateurs</code> table reference
     */
    public ProjetUtilisateurs() {
        this(DSL.name("projet_utilisateurs"), null);
    }

    /**
     * Create an aliased <code>madera.projet_utilisateurs</code> table reference
     */
    public ProjetUtilisateurs(String alias) {
        this(DSL.name(alias), PROJET_UTILISATEURS);
    }

    /**
     * Create an aliased <code>madera.projet_utilisateurs</code> table reference
     */
    public ProjetUtilisateurs(Name alias) {
        this(alias, PROJET_UTILISATEURS);
    }

    private ProjetUtilisateurs(Name alias, Table<ProjetUtilisateursRecord> aliased) {
        this(alias, aliased, null);
    }

    private ProjetUtilisateurs(Name alias, Table<ProjetUtilisateursRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> ProjetUtilisateurs(Table<O> child, ForeignKey<O, ProjetUtilisateursRecord> key) {
        super(child, key, PROJET_UTILISATEURS);
    }

    @Override
    public Schema getSchema() {
        return Madera.MADERA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FK_PROJET_IDX_2, Indexes.PROJET_UTILISATEURS_PKEY);
    }

    @Override
    public UniqueKey<ProjetUtilisateursRecord> getPrimaryKey() {
        return Keys.PROJET_UTILISATEURS_PKEY;
    }

    @Override
    public List<UniqueKey<ProjetUtilisateursRecord>> getKeys() {
        return Arrays.<UniqueKey<ProjetUtilisateursRecord>>asList(Keys.PROJET_UTILISATEURS_PKEY);
    }

    @Override
    public List<ForeignKey<ProjetUtilisateursRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ProjetUtilisateursRecord, ?>>asList(Keys.PROJET_UTILISATEURS__FK_UTILISATEURS, Keys.PROJET_UTILISATEURS__FK_PROJET_3);
    }

    public Utilisateur utilisateur() {
        return new Utilisateur(this, Keys.PROJET_UTILISATEURS__FK_UTILISATEURS);
    }

    public Projet projet() {
        return new Projet(this, Keys.PROJET_UTILISATEURS__FK_PROJET_3);
    }

    @Override
    public ProjetUtilisateurs as(String alias) {
        return new ProjetUtilisateurs(DSL.name(alias), this);
    }

    @Override
    public ProjetUtilisateurs as(Name alias) {
        return new ProjetUtilisateurs(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ProjetUtilisateurs rename(String name) {
        return new ProjetUtilisateurs(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ProjetUtilisateurs rename(Name name) {
        return new ProjetUtilisateurs(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
