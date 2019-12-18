/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables;


import com.madera.jooq.Indexes;
import com.madera.jooq.Keys;
import com.madera.jooq.Madera;
import com.madera.jooq.tables.records.ClientAdresseRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
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
public class ClientAdresse extends TableImpl<ClientAdresseRecord> {

    private static final long serialVersionUID = -295226481;

    /**
     * The reference instance of <code>madera.client_adresse</code>
     */
    public static final ClientAdresse CLIENT_ADRESSE = new ClientAdresse();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ClientAdresseRecord> getRecordType() {
        return ClientAdresseRecord.class;
    }

    /**
     * The column <code>madera.client_adresse.i_client_id</code>.
     */
    public final TableField<ClientAdresseRecord, Integer> I_CLIENT_ID = createField(DSL.name("i_client_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>madera.client_adresse.i_adresse_id</code>.
     */
    public final TableField<ClientAdresseRecord, Integer> I_ADRESSE_ID = createField(DSL.name("i_adresse_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>madera.client_adresse.b_adresse_facturation</code>.
     */
    public final TableField<ClientAdresseRecord, Boolean> B_ADRESSE_FACTURATION = createField(DSL.name("b_adresse_facturation"), org.jooq.impl.SQLDataType.BOOLEAN.defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * Create a <code>madera.client_adresse</code> table reference
     */
    public ClientAdresse() {
        this(DSL.name("client_adresse"), null);
    }

    /**
     * Create an aliased <code>madera.client_adresse</code> table reference
     */
    public ClientAdresse(String alias) {
        this(DSL.name(alias), CLIENT_ADRESSE);
    }

    /**
     * Create an aliased <code>madera.client_adresse</code> table reference
     */
    public ClientAdresse(Name alias) {
        this(alias, CLIENT_ADRESSE);
    }

    private ClientAdresse(Name alias, Table<ClientAdresseRecord> aliased) {
        this(alias, aliased, null);
    }

    private ClientAdresse(Name alias, Table<ClientAdresseRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> ClientAdresse(Table<O> child, ForeignKey<O, ClientAdresseRecord> key) {
        super(child, key, CLIENT_ADRESSE);
    }

    @Override
    public Schema getSchema() {
        return Madera.MADERA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FK_ADRESSE_IDX_2);
    }

    @Override
    public List<ForeignKey<ClientAdresseRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ClientAdresseRecord, ?>>asList(Keys.CLIENT_ADRESSE__FK_CLIENT_2, Keys.CLIENT_ADRESSE__FK_ADRESSE_2);
    }

    public Client client() {
        return new Client(this, Keys.CLIENT_ADRESSE__FK_CLIENT_2);
    }

    public Adresse adresse() {
        return new Adresse(this, Keys.CLIENT_ADRESSE__FK_ADRESSE_2);
    }

    @Override
    public ClientAdresse as(String alias) {
        return new ClientAdresse(DSL.name(alias), this);
    }

    @Override
    public ClientAdresse as(Name alias) {
        return new ClientAdresse(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ClientAdresse rename(String name) {
        return new ClientAdresse(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ClientAdresse rename(Name name) {
        return new ClientAdresse(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Boolean> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}