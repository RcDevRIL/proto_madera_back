/*
 * This file is generated by jOOQ.
 */
package com.madera.jooq;


import com.madera.jooq.tables.Adresse;
import com.madera.jooq.tables.Client;
import com.madera.jooq.tables.ClientAdresse;
import com.madera.jooq.tables.Composant;
import com.madera.jooq.tables.ComposantFournisseur;
import com.madera.jooq.tables.ComposantGroupe;
import com.madera.jooq.tables.ComposantReferentiel;
import com.madera.jooq.tables.DevisEtat;
import com.madera.jooq.tables.Fournisseur;
import com.madera.jooq.tables.Gammes;
import com.madera.jooq.tables.Module;
import com.madera.jooq.tables.ModuleComposant;
import com.madera.jooq.tables.ModuleReferentiel;
import com.madera.jooq.tables.Projet;
import com.madera.jooq.tables.ProjetModule;
import com.madera.jooq.tables.ProjetUtilisateurs;
import com.madera.jooq.tables.Role;
import com.madera.jooq.tables.Utilisateur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Madera extends SchemaImpl {

    private static final long serialVersionUID = -1217802379;

    /**
     * The reference instance of <code>madera</code>
     */
    public static final Madera MADERA = new Madera();

    /**
     * The table <code>madera.adresse</code>.
     */
    public final Adresse ADRESSE = com.madera.jooq.tables.Adresse.ADRESSE;

    /**
     * The table <code>madera.client</code>.
     */
    public final Client CLIENT = com.madera.jooq.tables.Client.CLIENT;

    /**
     * The table <code>madera.client_adresse</code>.
     */
    public final ClientAdresse CLIENT_ADRESSE = com.madera.jooq.tables.ClientAdresse.CLIENT_ADRESSE;

    /**
     * The table <code>madera.composant</code>.
     */
    public final Composant COMPOSANT = com.madera.jooq.tables.Composant.COMPOSANT;

    /**
     * The table <code>madera.composant_fournisseur</code>.
     */
    public final ComposantFournisseur COMPOSANT_FOURNISSEUR = com.madera.jooq.tables.ComposantFournisseur.COMPOSANT_FOURNISSEUR;

    /**
     * The table <code>madera.composant_groupe</code>.
     */
    public final ComposantGroupe COMPOSANT_GROUPE = com.madera.jooq.tables.ComposantGroupe.COMPOSANT_GROUPE;

    /**
     * The table <code>madera.composant_referentiel</code>.
     */
    public final ComposantReferentiel COMPOSANT_REFERENTIEL = com.madera.jooq.tables.ComposantReferentiel.COMPOSANT_REFERENTIEL;

    /**
     * The table <code>madera.devis_etat</code>.
     */
    public final DevisEtat DEVIS_ETAT = com.madera.jooq.tables.DevisEtat.DEVIS_ETAT;

    /**
     * The table <code>madera.fournisseur</code>.
     */
    public final Fournisseur FOURNISSEUR = com.madera.jooq.tables.Fournisseur.FOURNISSEUR;

    /**
     * The table <code>madera.gammes</code>.
     */
    public final Gammes GAMMES = com.madera.jooq.tables.Gammes.GAMMES;

    /**
     * The table <code>madera.module</code>.
     */
    public final Module MODULE = com.madera.jooq.tables.Module.MODULE;

    /**
     * The table <code>madera.module_composant</code>.
     */
    public final ModuleComposant MODULE_COMPOSANT = com.madera.jooq.tables.ModuleComposant.MODULE_COMPOSANT;

    /**
     * The table <code>madera.module_referentiel</code>.
     */
    public final ModuleReferentiel MODULE_REFERENTIEL = com.madera.jooq.tables.ModuleReferentiel.MODULE_REFERENTIEL;

    /**
     * The table <code>madera.projet</code>.
     */
    public final Projet PROJET = com.madera.jooq.tables.Projet.PROJET;

    /**
     * The table <code>madera.projet_module</code>.
     */
    public final ProjetModule PROJET_MODULE = com.madera.jooq.tables.ProjetModule.PROJET_MODULE;

    /**
     * The table <code>madera.projet_utilisateurs</code>.
     */
    public final ProjetUtilisateurs PROJET_UTILISATEURS = com.madera.jooq.tables.ProjetUtilisateurs.PROJET_UTILISATEURS;

    /**
     * The table <code>madera.role</code>.
     */
    public final Role ROLE = com.madera.jooq.tables.Role.ROLE;

    /**
     * The table <code>madera.utilisateur</code>.
     */
    public final Utilisateur UTILISATEUR = com.madera.jooq.tables.Utilisateur.UTILISATEUR;

    /**
     * No further instances allowed
     */
    private Madera() {
        super("madera", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.ADRESSE_I_ADRESSE_ID_SEQ,
            Sequences.CLIENT_I_CLIENT_ID_SEQ,
            Sequences.COMPOSANT_GROUPE_I_COMPOSANT_GROUPE_ID_SEQ,
            Sequences.COMPOSANT_I_COMPOSANT_ID_SEQ,
            Sequences.COMPOSANT_REFERENTIEL_I_COMPOSANT_REFERENTIEL_ID_SEQ,
            Sequences.DEVIS_ETAT_I_DEVIS_ETAT_ID_SEQ,
            Sequences.FOURNISSEUR_I_FOURNISSEUR_ID_SEQ,
            Sequences.GAMMES_I_GAMMES_ID_SEQ,
            Sequences.MODULE_I_MODULE_ID_SEQ,
            Sequences.MODULE_REFERENTIEL_I_MODULE_REFERENTIEL_ID_SEQ,
            Sequences.PROJET_I_PROJET_ID_SEQ,
            Sequences.PROJET_MODULE_I_PROJET_MODULE_ID_SEQ,
            Sequences.ROLE_I_ROLE_ID_SEQ,
            Sequences.UTILISATEUR_I_UTILISATEUR_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Adresse.ADRESSE,
            Client.CLIENT,
            ClientAdresse.CLIENT_ADRESSE,
            Composant.COMPOSANT,
            ComposantFournisseur.COMPOSANT_FOURNISSEUR,
            ComposantGroupe.COMPOSANT_GROUPE,
            ComposantReferentiel.COMPOSANT_REFERENTIEL,
            DevisEtat.DEVIS_ETAT,
            Fournisseur.FOURNISSEUR,
            Gammes.GAMMES,
            Module.MODULE,
            ModuleComposant.MODULE_COMPOSANT,
            ModuleReferentiel.MODULE_REFERENTIEL,
            Projet.PROJET,
            ProjetModule.PROJET_MODULE,
            ProjetUtilisateurs.PROJET_UTILISATEURS,
            Role.ROLE,
            Utilisateur.UTILISATEUR);
    }
}
