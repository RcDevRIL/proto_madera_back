/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq.tables.records;


import com.madera.jooq.tables.Utilisateur;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
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
public class UtilisateurRecord extends UpdatableRecordImpl<UtilisateurRecord> implements Record10<Integer, String, String, String, String, String, String, String, Integer, String> {

    private static final long serialVersionUID = -1884770243;

    /**
     * Setter for <code>madera.utilisateur.i_utilisateur_id</code>.
     */
    public void setIUtilisateurId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>madera.utilisateur.i_utilisateur_id</code>.
     */
    public Integer getIUtilisateurId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>madera.utilisateur.v_nom</code>.
     */
    public void setVNom(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>madera.utilisateur.v_nom</code>.
     */
    public String getVNom() {
        return (String) get(1);
    }

    /**
     * Setter for <code>madera.utilisateur.v_prenom</code>.
     */
    public void setVPrenom(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>madera.utilisateur.v_prenom</code>.
     */
    public String getVPrenom() {
        return (String) get(2);
    }

    /**
     * Setter for <code>madera.utilisateur.v_date_naissance</code>.
     */
    public void setVDateNaissance(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>madera.utilisateur.v_date_naissance</code>.
     */
    public String getVDateNaissance() {
        return (String) get(3);
    }

    /**
     * Setter for <code>madera.utilisateur.v_mail</code>.
     */
    public void setVMail(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>madera.utilisateur.v_mail</code>.
     */
    public String getVMail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>madera.utilisateur.v_tel</code>.
     */
    public void setVTel(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>madera.utilisateur.v_tel</code>.
     */
    public String getVTel() {
        return (String) get(5);
    }

    /**
     * Setter for <code>madera.utilisateur.v_login</code>.
     */
    public void setVLogin(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>madera.utilisateur.v_login</code>.
     */
    public String getVLogin() {
        return (String) get(6);
    }

    /**
     * Setter for <code>madera.utilisateur.v_password</code>.
     */
    public void setVPassword(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>madera.utilisateur.v_password</code>.
     */
    public String getVPassword() {
        return (String) get(7);
    }

    /**
     * Setter for <code>madera.utilisateur.i_role_id</code>.
     */
    public void setIRoleId(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>madera.utilisateur.i_role_id</code>.
     */
    public Integer getIRoleId() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>madera.utilisateur.v_token</code>.
     */
    public void setVToken(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>madera.utilisateur.v_token</code>.
     */
    public String getVToken() {
        return (String) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, String, String, String, String, String, String, String, Integer, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    public Row10<Integer, String, String, String, String, String, String, String, Integer, String> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Utilisateur.UTILISATEUR.I_UTILISATEUR_ID;
    }

    @Override
    public Field<String> field2() {
        return Utilisateur.UTILISATEUR.V_NOM;
    }

    @Override
    public Field<String> field3() {
        return Utilisateur.UTILISATEUR.V_PRENOM;
    }

    @Override
    public Field<String> field4() {
        return Utilisateur.UTILISATEUR.V_DATE_NAISSANCE;
    }

    @Override
    public Field<String> field5() {
        return Utilisateur.UTILISATEUR.V_MAIL;
    }

    @Override
    public Field<String> field6() {
        return Utilisateur.UTILISATEUR.V_TEL;
    }

    @Override
    public Field<String> field7() {
        return Utilisateur.UTILISATEUR.V_LOGIN;
    }

    @Override
    public Field<String> field8() {
        return Utilisateur.UTILISATEUR.V_PASSWORD;
    }

    @Override
    public Field<Integer> field9() {
        return Utilisateur.UTILISATEUR.I_ROLE_ID;
    }

    @Override
    public Field<String> field10() {
        return Utilisateur.UTILISATEUR.V_TOKEN;
    }

    @Override
    public Integer component1() {
        return getIUtilisateurId();
    }

    @Override
    public String component2() {
        return getVNom();
    }

    @Override
    public String component3() {
        return getVPrenom();
    }

    @Override
    public String component4() {
        return getVDateNaissance();
    }

    @Override
    public String component5() {
        return getVMail();
    }

    @Override
    public String component6() {
        return getVTel();
    }

    @Override
    public String component7() {
        return getVLogin();
    }

    @Override
    public String component8() {
        return getVPassword();
    }

    @Override
    public Integer component9() {
        return getIRoleId();
    }

    @Override
    public String component10() {
        return getVToken();
    }

    @Override
    public Integer value1() {
        return getIUtilisateurId();
    }

    @Override
    public String value2() {
        return getVNom();
    }

    @Override
    public String value3() {
        return getVPrenom();
    }

    @Override
    public String value4() {
        return getVDateNaissance();
    }

    @Override
    public String value5() {
        return getVMail();
    }

    @Override
    public String value6() {
        return getVTel();
    }

    @Override
    public String value7() {
        return getVLogin();
    }

    @Override
    public String value8() {
        return getVPassword();
    }

    @Override
    public Integer value9() {
        return getIRoleId();
    }

    @Override
    public String value10() {
        return getVToken();
    }

    @Override
    public UtilisateurRecord value1(Integer value) {
        setIUtilisateurId(value);
        return this;
    }

    @Override
    public UtilisateurRecord value2(String value) {
        setVNom(value);
        return this;
    }

    @Override
    public UtilisateurRecord value3(String value) {
        setVPrenom(value);
        return this;
    }

    @Override
    public UtilisateurRecord value4(String value) {
        setVDateNaissance(value);
        return this;
    }

    @Override
    public UtilisateurRecord value5(String value) {
        setVMail(value);
        return this;
    }

    @Override
    public UtilisateurRecord value6(String value) {
        setVTel(value);
        return this;
    }

    @Override
    public UtilisateurRecord value7(String value) {
        setVLogin(value);
        return this;
    }

    @Override
    public UtilisateurRecord value8(String value) {
        setVPassword(value);
        return this;
    }

    @Override
    public UtilisateurRecord value9(Integer value) {
        setIRoleId(value);
        return this;
    }

    @Override
    public UtilisateurRecord value10(String value) {
        setVToken(value);
        return this;
    }

    @Override
    public UtilisateurRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, Integer value9, String value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UtilisateurRecord
     */
    public UtilisateurRecord() {
        super(Utilisateur.UTILISATEUR);
    }

    /**
     * Create a detached, initialised UtilisateurRecord
     */
    public UtilisateurRecord(Integer iUtilisateurId, String vNom, String vPrenom, String vDateNaissance, String vMail, String vTel, String vLogin, String vPassword, Integer iRoleId, String vToken) {
        super(Utilisateur.UTILISATEUR);

        set(0, iUtilisateurId);
        set(1, vNom);
        set(2, vPrenom);
        set(3, vDateNaissance);
        set(4, vMail);
        set(5, vTel);
        set(6, vLogin);
        set(7, vPassword);
        set(8, iRoleId);
        set(9, vToken);
    }
}
