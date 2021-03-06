/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables;


import com.madera.jooq.Indexes;
import com.madera.jooq.Keys;
import com.madera.jooq.Madera;
import com.madera.jooq.tables.records.ModuleComposantRecord;

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
public class ModuleComposant extends TableImpl<ModuleComposantRecord> {

    private static final long serialVersionUID = 1160266152;

    /**
     * The reference instance of <code>madera.module_composant</code>
     */
    public static final ModuleComposant MODULE_COMPOSANT = new ModuleComposant();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ModuleComposantRecord> getRecordType() {
        return ModuleComposantRecord.class;
    }

    /**
     * The column <code>madera.module_composant.i_module_id</code>.
     */
    public final TableField<ModuleComposantRecord, Integer> I_MODULE_ID = createField(DSL.name("i_module_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>madera.module_composant.i_composant_id</code>.
     */
    public final TableField<ModuleComposantRecord, Integer> I_COMPOSANT_ID = createField(DSL.name("i_composant_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>madera.module_composant.i_ordre</code>.
     */
    public final TableField<ModuleComposantRecord, Integer> I_ORDRE = createField(DSL.name("i_ordre"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>madera.module_composant</code> table reference
     */
    public ModuleComposant() {
        this(DSL.name("module_composant"), null);
    }

    /**
     * Create an aliased <code>madera.module_composant</code> table reference
     */
    public ModuleComposant(String alias) {
        this(DSL.name(alias), MODULE_COMPOSANT);
    }

    /**
     * Create an aliased <code>madera.module_composant</code> table reference
     */
    public ModuleComposant(Name alias) {
        this(alias, MODULE_COMPOSANT);
    }

    private ModuleComposant(Name alias, Table<ModuleComposantRecord> aliased) {
        this(alias, aliased, null);
    }

    private ModuleComposant(Name alias, Table<ModuleComposantRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> ModuleComposant(Table<O> child, ForeignKey<O, ModuleComposantRecord> key) {
        super(child, key, MODULE_COMPOSANT);
    }

    @Override
    public Schema getSchema() {
        return Madera.MADERA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FK_COMPOSANT_IDX);
    }

    @Override
    public List<ForeignKey<ModuleComposantRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ModuleComposantRecord, ?>>asList(Keys.MODULE_COMPOSANT__FK_MODULE, Keys.MODULE_COMPOSANT__FK_COMPOSANT);
    }

    public Module module() {
        return new Module(this, Keys.MODULE_COMPOSANT__FK_MODULE);
    }

    public Composant composant() {
        return new Composant(this, Keys.MODULE_COMPOSANT__FK_COMPOSANT);
    }

    @Override
    public ModuleComposant as(String alias) {
        return new ModuleComposant(DSL.name(alias), this);
    }

    @Override
    public ModuleComposant as(Name alias) {
        return new ModuleComposant(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleComposant rename(String name) {
        return new ModuleComposant(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleComposant rename(Name name) {
        return new ModuleComposant(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
