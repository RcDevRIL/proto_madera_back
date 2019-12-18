/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables;


import com.madera.jooq.Indexes;
import com.madera.jooq.Keys;
import com.madera.jooq.Madera;
import com.madera.jooq.tables.records.ProjetRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
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
public class Projet extends TableImpl<ProjetRecord> {

    private static final long serialVersionUID = 1951085278;

    /**
     * The reference instance of <code>madera.projet</code>
     */
    public static final Projet PROJET = new Projet();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProjetRecord> getRecordType() {
        return ProjetRecord.class;
    }

    /**
     * The column <code>madera.projet.i_projet_id</code>.
     */
    public final TableField<ProjetRecord, Integer> I_PROJET_ID = createField(DSL.name("i_projet_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('madera.projet_i_projet_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>madera.projet.i_client_id</code>.
     */
    public final TableField<ProjetRecord, Integer> I_CLIENT_ID = createField(DSL.name("i_client_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>madera.projet.v_nom_projet</code>.
     */
    public final TableField<ProjetRecord, String> V_NOM_PROJET = createField(DSL.name("v_nom_projet"), org.jooq.impl.SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>madera.projet.v_ref_projet</code>.
     */
    public final TableField<ProjetRecord, String> V_REF_PROJET = createField(DSL.name("v_ref_projet"), org.jooq.impl.SQLDataType.VARCHAR(45), this, "");

    /**
     * The column <code>madera.projet.d_date_projet</code>.
     */
    public final TableField<ProjetRecord, Date> D_DATE_PROJET = createField(DSL.name("d_date_projet"), org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>madera.projet.v_signature_projet</code>.
     */
    public final TableField<ProjetRecord, byte[]> V_SIGNATURE_PROJET = createField(DSL.name("v_signature_projet"), org.jooq.impl.SQLDataType.BLOB, this, "");

    /**
     * The column <code>madera.projet.i_devis_etat_id</code>.
     */
    public final TableField<ProjetRecord, Integer> I_DEVIS_ETAT_ID = createField(DSL.name("i_devis_etat_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>madera.projet.f_prix</code>.
     */
    public final TableField<ProjetRecord, Double> F_PRIX = createField(DSL.name("f_prix"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * Create a <code>madera.projet</code> table reference
     */
    public Projet() {
        this(DSL.name("projet"), null);
    }

    /**
     * Create an aliased <code>madera.projet</code> table reference
     */
    public Projet(String alias) {
        this(DSL.name(alias), PROJET);
    }

    /**
     * Create an aliased <code>madera.projet</code> table reference
     */
    public Projet(Name alias) {
        this(alias, PROJET);
    }

    private Projet(Name alias, Table<ProjetRecord> aliased) {
        this(alias, aliased, null);
    }

    private Projet(Name alias, Table<ProjetRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Projet(Table<O> child, ForeignKey<O, ProjetRecord> key) {
        super(child, key, PROJET);
    }

    @Override
    public Schema getSchema() {
        return Madera.MADERA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FK_CLIENT_IDX, Indexes.PROJET_PKEY);
    }

    @Override
    public Identity<ProjetRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PROJET;
    }

    @Override
    public UniqueKey<ProjetRecord> getPrimaryKey() {
        return Keys.PROJET_PKEY;
    }

    @Override
    public List<UniqueKey<ProjetRecord>> getKeys() {
        return Arrays.<UniqueKey<ProjetRecord>>asList(Keys.PROJET_PKEY);
    }

    @Override
    public List<ForeignKey<ProjetRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ProjetRecord, ?>>asList(Keys.PROJET__FK_CLIENT, Keys.PROJET__FK_DEVIS_ETAT);
    }

    public Client client() {
        return new Client(this, Keys.PROJET__FK_CLIENT);
    }

    public DevisEtat devisEtat() {
        return new DevisEtat(this, Keys.PROJET__FK_DEVIS_ETAT);
    }

    @Override
    public Projet as(String alias) {
        return new Projet(DSL.name(alias), this);
    }

    @Override
    public Projet as(Name alias) {
        return new Projet(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Projet rename(String name) {
        return new Projet(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Projet rename(Name name) {
        return new Projet(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, Integer, String, String, Date, byte[], Integer, Double> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}