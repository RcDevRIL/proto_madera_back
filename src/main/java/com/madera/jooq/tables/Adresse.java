/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables;


import com.madera.jooq.Indexes;
import com.madera.jooq.Keys;
import com.madera.jooq.Madera;
import com.madera.jooq.tables.records.AdresseRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
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
public class Adresse extends TableImpl<AdresseRecord> {

    private static final long serialVersionUID = -180960328;

    /**
     * The reference instance of <code>madera.adresse</code>
     */
    public static final Adresse ADRESSE = new Adresse();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AdresseRecord> getRecordType() {
        return AdresseRecord.class;
    }

    /**
     * The column <code>madera.adresse.i_adresse_id</code>.
     */
    public final TableField<AdresseRecord, Integer> I_ADRESSE_ID = createField(DSL.name("i_adresse_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('madera.adresse_i_adresse_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>madera.adresse.v_ville</code>.
     */
    public final TableField<AdresseRecord, String> V_VILLE = createField(DSL.name("v_ville"), org.jooq.impl.SQLDataType.VARCHAR(45), this, "");

    /**
     * The column <code>madera.adresse.v_code_postale</code>.
     */
    public final TableField<AdresseRecord, String> V_CODE_POSTALE = createField(DSL.name("v_code_postale"), org.jooq.impl.SQLDataType.VARCHAR(5), this, "");

    /**
     * The column <code>madera.adresse.v_rue</code>.
     */
    public final TableField<AdresseRecord, String> V_RUE = createField(DSL.name("v_rue"), org.jooq.impl.SQLDataType.VARCHAR(45), this, "");

    /**
     * The column <code>madera.adresse.v_complement</code>.
     */
    public final TableField<AdresseRecord, String> V_COMPLEMENT = createField(DSL.name("v_complement"), org.jooq.impl.SQLDataType.VARCHAR(45), this, "");

    /**
     * The column <code>madera.adresse.v_numero</code>.
     */
    public final TableField<AdresseRecord, String> V_NUMERO = createField(DSL.name("v_numero"), org.jooq.impl.SQLDataType.VARCHAR(5), this, "");

    /**
     * Create a <code>madera.adresse</code> table reference
     */
    public Adresse() {
        this(DSL.name("adresse"), null);
    }

    /**
     * Create an aliased <code>madera.adresse</code> table reference
     */
    public Adresse(String alias) {
        this(DSL.name(alias), ADRESSE);
    }

    /**
     * Create an aliased <code>madera.adresse</code> table reference
     */
    public Adresse(Name alias) {
        this(alias, ADRESSE);
    }

    private Adresse(Name alias, Table<AdresseRecord> aliased) {
        this(alias, aliased, null);
    }

    private Adresse(Name alias, Table<AdresseRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Adresse(Table<O> child, ForeignKey<O, AdresseRecord> key) {
        super(child, key, ADRESSE);
    }

    @Override
    public Schema getSchema() {
        return Madera.MADERA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ADRESSE_PKEY);
    }

    @Override
    public Identity<AdresseRecord, Integer> getIdentity() {
        return Keys.IDENTITY_ADRESSE;
    }

    @Override
    public UniqueKey<AdresseRecord> getPrimaryKey() {
        return Keys.ADRESSE_PKEY;
    }

    @Override
    public List<UniqueKey<AdresseRecord>> getKeys() {
        return Arrays.<UniqueKey<AdresseRecord>>asList(Keys.ADRESSE_PKEY);
    }

    @Override
    public Adresse as(String alias) {
        return new Adresse(DSL.name(alias), this);
    }

    @Override
    public Adresse as(Name alias) {
        return new Adresse(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Adresse rename(String name) {
        return new Adresse(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Adresse rename(Name name) {
        return new Adresse(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}