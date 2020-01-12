package com.madera.api.utils;

import com.madera.api.models.Module;
import com.madera.api.models.*;
import com.madera.api.repository.UserRepository;
import com.madera.api.security.SecurityUser;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.madera.jooq.Tables.*;
import static com.madera.jooq.tables.Client.CLIENT;
import static com.madera.jooq.tables.DevisEtat.DEVIS_ETAT;

/**
 * Classe permettant d'exposer des méthodes utiles.
 *
 * Elle est principalement utilisé pour mapper des données actuellement.
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.4-RELEASE
 */
public class Helper {

    UserRepository userRepository = new UserRepository();

    /**
     *
     * @param record resultat requete sql
     * @return User
     */
    public static User recordToUser(Record record) {
        User user = new User();
        user.setUtilisateurId(record.get(UTILISATEUR.I_UTILISATEUR_ID));
        user.setLogin(record.get(UTILISATEUR.V_LOGIN));
        user.setToken(record.get(UTILISATEUR.V_TOKEN));
        return user;
    }

    /**
     *
     * @param record resultat requete sql
     * @return projet
     */
    public static Projet recordToProjet(Record record) {
        Projet projet = new Projet();
        projet.setProjetId(record.get(PROJET.I_PROJET_ID));
        projet.setNomProjet(record.get(PROJET.V_NOM_PROJET));
        projet.setRefProjet(record.get(PROJET.V_REF_PROJET));
        projet.setDateProjet(record.get(PROJET.D_DATE_PROJET));
        projet.setPrixTotal(record.get(PROJET.F_PRIX_TOTAL) == null ? 0 : record.get(PROJET.F_PRIX_TOTAL));
        projet.setClientId(record.get(PROJET.I_CLIENT_ID));
        projet.setDevisEtatId(record.get(PROJET.I_DEVIS_ETAT_ID));
        projet.setSynchro(record.get(PROJET.IS_SYNCHRO));
        return projet;
    }

    /**
     *
     * @param record resultat requete sql
     * @return Client
     */
    public static Client recordToClient(Record record) {
        Client client = new Client();
        client.setId(record.get(CLIENT.I_CLIENT_ID));
        client.setNom(record.get(CLIENT.V_NOM));
        client.setPrenom(record.get(CLIENT.V_PRENOM));
        client.setNumTel(record.get(CLIENT.V_TEL));
        client.setMail(record.get(CLIENT.V_MAIL));
        return client;
    }

    /**
     *
     * @param record resultat requete sql
     * @return DevisEtat
     */
    public static DevisEtat recordToDevisEtat(Record record) {
        DevisEtat devisEtat = new DevisEtat();
        devisEtat.setDevisEtatId(record.get(DEVIS_ETAT.I_DEVIS_ETAT_ID));
        devisEtat.setDevisEtatLibelle(record.get(DEVIS_ETAT.V_DEVIS_ETAT_LIBELLE));
        devisEtat.setPourcentageSomme(record.get(DEVIS_ETAT.I_POURCENTAGE_SOMME));
        return devisEtat;
    }

    /**
     * Construct a instance of securityUser
     * 
     * @param record resultat requete sql
     * @return SecurityUser
     */
    public static SecurityUser recordToSecurityUser(Record record) {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(record.get(UTILISATEUR.V_LOGIN));
        securityUser.setRole(record.get(ROLE.V_LIBELLE_ROLE));
        return securityUser;
    }

    // TODO A supprimer ?
    public static ComposantReferentiel recordToComposantReferentiel(Record record) {
        ComposantReferentiel composantReferentiel = new ComposantReferentiel();
        composantReferentiel.setComposantReferentielId(record.get(COMPOSANT_REFERENTIEL.I_COMPOSANT_REFERENTIEL_ID));
        composantReferentiel.setCaracteristiqueReferentiel(record.get(COMPOSANT_REFERENTIEL.V_CARACTERISTIQUE));
        composantReferentiel.setUniteUsage(record.get(COMPOSANT_REFERENTIEL.V_UNITE_USAGE));
        return composantReferentiel;
    }

    /**
     *
     * @param record resultat requete sql
     * @return ComposantGroupe
     */
    public static ComposantGroupe recordToComposantGroupe(Record record) {
        ComposantGroupe composantGroupe = new ComposantGroupe();
        composantGroupe.setComposantGroupeId(record.get(COMPOSANT_GROUPE.I_COMPOSANT_GROUPE_ID));
        composantGroupe.setLibelleGroupe(record.get(COMPOSANT_GROUPE.V_LIBELLE_GROUPE));
        return composantGroupe;
    }

    /**
     *
     * @param record resultat requete sql
     * @return Composant
     */
    public static Composant recordToComposant(Record record) {
        Composant composant = new Composant();
        composant.setComposantId(record.get(COMPOSANT.I_COMPOSANT_ID));
        // ComposantGroupe
        composant.setComposantGroupeId(record.get(COMPOSANT_GROUPE.I_COMPOSANT_GROUPE_ID));
        composant.setLibelleGroupe(record.get(COMPOSANT_GROUPE.V_LIBELLE_GROUPE));
        // Création de l'objet composantReferentiel
        composant.setComposantReferentielId(record.get(COMPOSANT_REFERENTIEL.I_COMPOSANT_REFERENTIEL_ID));
        composant.setCaracteristiqueReferentiel(record.get(COMPOSANT_REFERENTIEL.V_CARACTERISTIQUE));
        composant.setUniteUsage(record.get(COMPOSANT_REFERENTIEL.V_UNITE_USAGE));
        composant.setLibelle(record.get(COMPOSANT.V_LIBELLE));
        // Section peut être null
        composant.setSection(Optional.ofNullable(record.get(COMPOSANT.F_SECTION)));
        composant.setPrixComposant(record.get(COMPOSANT.F_COMPOSANT_PRIX));
        return composant;
    }

    /**
     *
     * @param record resultat requete sql
     * @return Module
     */
    public static Module recordToModule(Record record) {
        Module module = new Module();
        module.setModuleId(record.get(MODULE.I_MODULE_ID));
        module.setGammeId(record.get(GAMMES.I_GAMMES_ID));
        module.setModuleReferentielId(record.get(MODULE_REFERENTIEL.I_MODULE_REFERENTIEL_ID));
        module.setCaracteristiqueReferentiel(record.get(MODULE_REFERENTIEL.V_CARACTERISTIQUE));
        module.setUniteUsage(record.get(MODULE_REFERENTIEL.V_UNITE_USAGE));
        module.setNom(record.get(MODULE.V_NOM));
        module.setNatureModule(record.get(MODULE.V_NATURE_MODULE));
        return module;
    }

    /**
     *
     * @param record resultat requete sql
     * @return gamme
     */
    public static Gamme recordToGamme(Record record) {
        Gamme gamme = new Gamme();
        gamme.setGammeId(record.get(GAMMES.I_GAMMES_ID));
        gamme.setLibelleGammes(record.get(GAMMES.V_LIBELLE_GAMMES));
        return gamme;
    }

    public static ModuleReferentiel recordToModuleReferentiel(Record record) {
        ModuleReferentiel moduleReferentiel = new ModuleReferentiel();
        moduleReferentiel.setModuleReferentielId(record.get(MODULE_REFERENTIEL.I_MODULE_REFERENTIEL_ID));
        moduleReferentiel.setCaracteristiqueReferentiel(record.get(MODULE_REFERENTIEL.V_CARACTERISTIQUE));
        moduleReferentiel.setUniteUsage(record.get(MODULE_REFERENTIEL.V_UNITE_USAGE));
        return moduleReferentiel;
    }

    /**
     *
     * @param record resultat requete sql
     * @return Gamme
     */
    public static ModuleComposant recordToModuleComposant(Record record) {
        ModuleComposant moduleComposant = new ModuleComposant();
        moduleComposant.setModuleId(record.get(MODULE_COMPOSANT.I_MODULE_ID));
        moduleComposant.setComposantId(record.get(MODULE_COMPOSANT.I_COMPOSANT_ID));
        moduleComposant.setOrdre(record.get(MODULE_COMPOSANT.I_ORDRE));
        return moduleComposant;
    }

    /**
     *
     * @param record resultat requete sql
     * @return Produit
     */
    public static Produit recordToProduit(Record record) {
        Produit produit = new Produit();
        produit.setProduitId(record.get(PRODUIT.I_PRODUIT_ID));
        produit.setGammesId(record.get(PRODUIT.I_GAMMES_ID));
        produit.setProduitNom(record.get(PRODUIT.V_PRODUIT_NOM));
        produit.setPrixProduit(record.get(PRODUIT.F_PRIX_PRODUIT) == null ? 0 : record.get(PRODUIT.F_PRIX_PRODUIT));
        produit.setModele(record.get(PRODUIT.B_MODELE));
        return produit;
    }

    /**
     *
     * @param record resultat requete sql
     * @return ProjetProduits
     */
    public static ProjetProduits recordToProjetProduits(Record record) {
        ProjetProduits projetProduits = new ProjetProduits();
        projetProduits.setProjetId(record.get(PROJET_PRODUITS.I_PROJET_ID));
        projetProduits.setProduitId(record.get(PROJET_PRODUITS.I_PRODUIT_ID));
        return projetProduits;
    }

    /**
     *
     * @param record resultat requete sql
     * @return ProduitModule
     */
    public static ProduitModule recordToProduitModule(Record record) {
        ProduitModule produitModule = new ProduitModule();
        produitModule.setProduitModuleId(record.get(PRODUIT_MODULE.I_PRODUIT_MODULE_ID));
        produitModule.setProduitId(record.get(PRODUIT_MODULE.I_PRODUIT_ID));
        produitModule.setModuleId(record.get(PRODUIT_MODULE.I_MODULE_ID));
        produitModule.setProduitModuleNom(record.get(PRODUIT_MODULE.V_PRODUIT_MODULE_NOM));
        produitModule.setProduitModuleAngle(record.get(PRODUIT_MODULE.V_PRODUIT_MODULE_ANGLE));
        produitModule.setProduitModuleSectionLongueur(record.get(PRODUIT_MODULE.J_SECTION_LONGUEUR, String.class));
        produitModule.setPrixModule(record.get(PRODUIT_MODULE.F_PRIX));
        return produitModule;
    }

    /**
     *
     * @param record resultat requete sql
     * @return ClientAdresse
     */
    public static ClientAdresse recordToClientAdresse(Record record) {
        ClientAdresse clientAdresse = new ClientAdresse();
        clientAdresse.setClientId(record.get(CLIENT_ADRESSE.I_CLIENT_ID));
        clientAdresse.setAdresseId(record.get(CLIENT_ADRESSE.I_ADRESSE_ID));
        clientAdresse.setAdresseFacturation(record.get(CLIENT_ADRESSE.B_ADRESSE_FACTURATION));
        return clientAdresse;
    }

    /**
     *
     * @param record resultat requete sql
     * @return Adresse
     */
    public static Adresse recordToAdresse(Record record) {
        Adresse adresse = new Adresse();
        adresse.setAdresseId(record.get(ADRESSE.I_ADRESSE_ID));
        adresse.setVille(record.get(ADRESSE.V_VILLE));
        adresse.setCodePostale(record.get(ADRESSE.V_CODE_POSTALE));
        adresse.setRue(record.get(ADRESSE.V_RUE));
        adresse.setComplement(record.get(ADRESSE.V_COMPLEMENT));
        adresse.setNumero(record.get(ADRESSE.V_NUMERO));
        return adresse;
    }

    public static Utilisateur recordToUtilisateur(Record record) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(record.get(UTILISATEUR.V_NOM));
        utilisateur.setPrenom(record.get(UTILISATEUR.V_PRENOM));
        utilisateur.setMail(record.get(UTILISATEUR.V_MAIL));
        utilisateur.setLogin(record.get(UTILISATEUR.V_LOGIN));
        utilisateur.setTel(record.get(UTILISATEUR.V_TEL));
        return utilisateur;
    }
}
