/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables;


import com.madera.jooq.Indexes;
import com.madera.jooq.Keys;
import com.madera.jooq.Madera;
import com.madera.jooq.tables.records.DevisEtatRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
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
public class DevisEtat extends TableImpl<DevisEtatRecord> {

    private static final long serialVersionUID = -584417720;

    /**
     * The reference instance of <code>madera.devis_etat</code>
     */
    public static final DevisEtat DEVIS_ETAT = new DevisEtat();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DevisEtatRecord> getRecordType() {
        return DevisEtatRecord.class;
    }

    /**
     * The column <code>madera.devis_etat.i_devis_etat_id</code>.
     */
    public final TableField<DevisEtatRecord, Integer> I_DEVIS_ETAT_ID = createField(DSL.name("i_devis_etat_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('madera.devis_etat_i_devis_etat_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>madera.devis_etat.v_devis_etat_libelle</code>.
     */
    public final TableField<DevisEtatRecord, String> V_DEVIS_ETAT_LIBELLE = createField(DSL.name("v_devis_etat_libelle"), org.jooq.impl.SQLDataType.VARCHAR(150), this, "");

    /**
     * The column <code>madera.devis_etat.i_pourcentage_somme</code>.
     */
    public final TableField<DevisEtatRecord, Integer> I_POURCENTAGE_SOMME = createField(DSL.name("i_pourcentage_somme"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>madera.devis_etat</code> table reference
     */
    public DevisEtat() {
        this(DSL.name("devis_etat"), null);
    }

    /**
     * Create an aliased <code>madera.devis_etat</code> table reference
     */
    public DevisEtat(String alias) {
        this(DSL.name(alias), DEVIS_ETAT);
    }

    /**
     * Create an aliased <code>madera.devis_etat</code> table reference
     */
    public DevisEtat(Name alias) {
        this(alias, DEVIS_ETAT);
    }

    private DevisEtat(Name alias, Table<DevisEtatRecord> aliased) {
        this(alias, aliased, null);
    }

    private DevisEtat(Name alias, Table<DevisEtatRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> DevisEtat(Table<O> child, ForeignKey<O, DevisEtatRecord> key) {
        super(child, key, DEVIS_ETAT);
    }

    @Override
    public Schema getSchema() {
        return Madera.MADERA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.DEVIS_ETAT_PKEY);
    }

    @Override
    public Identity<DevisEtatRecord, Integer> getIdentity() {
        return Keys.IDENTITY_DEVIS_ETAT;
    }

    @Override
    public UniqueKey<DevisEtatRecord> getPrimaryKey() {
        return Keys.DEVIS_ETAT_PKEY;
    }

    @Override
    public List<UniqueKey<DevisEtatRecord>> getKeys() {
        return Arrays.<UniqueKey<DevisEtatRecord>>asList(Keys.DEVIS_ETAT_PKEY);
    }

    @Override
    public DevisEtat as(String alias) {
        return new DevisEtat(DSL.name(alias), this);
    }

    @Override
    public DevisEtat as(Name alias) {
        return new DevisEtat(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DevisEtat rename(String name) {
        return new DevisEtat(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DevisEtat rename(Name name) {
        return new DevisEtat(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}