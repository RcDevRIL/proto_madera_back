/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables;


import com.madera.jooq.Indexes;
import com.madera.jooq.Keys;
import com.madera.jooq.Madera;
import com.madera.jooq.tables.records.ModuleRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
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
public class Module extends TableImpl<ModuleRecord> {

    private static final long serialVersionUID = 838085000;

    /**
     * The reference instance of <code>madera.module</code>
     */
    public static final Module MODULE = new Module();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ModuleRecord> getRecordType() {
        return ModuleRecord.class;
    }

    /**
     * The column <code>madera.module.i_module_id</code>.
     */
    public final TableField<ModuleRecord, Integer> I_MODULE_ID = createField(DSL.name("i_module_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('madera.module_i_module_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>madera.module.i_gammes_id</code>.
     */
    public final TableField<ModuleRecord, Integer> I_GAMMES_ID = createField(DSL.name("i_gammes_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>madera.module.i_module_referentiel_id</code>.
     */
    public final TableField<ModuleRecord, Integer> I_MODULE_REFERENTIEL_ID = createField(DSL.name("i_module_referentiel_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>madera.module.v_nom</code>.
     */
    public final TableField<ModuleRecord, String> V_NOM = createField(DSL.name("v_nom"), org.jooq.impl.SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>madera.module.v_angle</code>.
     */
    public final TableField<ModuleRecord, String> V_ANGLE = createField(DSL.name("v_angle"), org.jooq.impl.SQLDataType.VARCHAR(25), this, "");

    /**
     * The column <code>madera.module.v_nature_module</code>.
     */
    public final TableField<ModuleRecord, String> V_NATURE_MODULE = createField(DSL.name("v_nature_module"), org.jooq.impl.SQLDataType.VARCHAR(45), this, "");

    /**
     * The column <code>madera.module.b_modele</code>.
     */
    public final TableField<ModuleRecord, Boolean> B_MODELE = createField(DSL.name("b_modele"), org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * Create a <code>madera.module</code> table reference
     */
    public Module() {
        this(DSL.name("module"), null);
    }

    /**
     * Create an aliased <code>madera.module</code> table reference
     */
    public Module(String alias) {
        this(DSL.name(alias), MODULE);
    }

    /**
     * Create an aliased <code>madera.module</code> table reference
     */
    public Module(Name alias) {
        this(alias, MODULE);
    }

    private Module(Name alias, Table<ModuleRecord> aliased) {
        this(alias, aliased, null);
    }

    private Module(Name alias, Table<ModuleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Module(Table<O> child, ForeignKey<O, ModuleRecord> key) {
        super(child, key, MODULE);
    }

    @Override
    public Schema getSchema() {
        return Madera.MADERA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FK_GAMMES_IDX, Indexes.FK_MODULE_REFERENTIEL_IDX, Indexes.MODULE_PKEY);
    }

    @Override
    public Identity<ModuleRecord, Integer> getIdentity() {
        return Keys.IDENTITY_MODULE;
    }

    @Override
    public UniqueKey<ModuleRecord> getPrimaryKey() {
        return Keys.MODULE_PKEY;
    }

    @Override
    public List<UniqueKey<ModuleRecord>> getKeys() {
        return Arrays.<UniqueKey<ModuleRecord>>asList(Keys.MODULE_PKEY);
    }

    @Override
    public List<ForeignKey<ModuleRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ModuleRecord, ?>>asList(Keys.MODULE__FK_GAMMES, Keys.MODULE__FK_MODULE_REFERENTIEL);
    }

    public Gammes gammes() {
        return new Gammes(this, Keys.MODULE__FK_GAMMES);
    }

    public ModuleReferentiel moduleReferentiel() {
        return new ModuleReferentiel(this, Keys.MODULE__FK_MODULE_REFERENTIEL);
    }

    @Override
    public Module as(String alias) {
        return new Module(DSL.name(alias), this);
    }

    @Override
    public Module as(Name alias) {
        return new Module(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Module rename(String name) {
        return new Module(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Module rename(Name name) {
        return new Module(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, Integer, String, String, String, Boolean> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}