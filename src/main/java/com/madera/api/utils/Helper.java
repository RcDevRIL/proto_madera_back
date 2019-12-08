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
 * @version 0.1-RELEASE
 */
public class Helper {

    UserRepository userRepository = new UserRepository();

    public static User RecordToUser(Record record) {
        User user = new User();
        user.setUtilisateurId(record.get(UTILISATEUR.I_UTILISATEUR_ID));
        user.setLogin(record.get(UTILISATEUR.V_LOGIN));
        user.setToken(record.get(UTILISATEUR.V_TOKEN));
        return user;
    }

    public static Projet RecordToProject(Record record) {
        Projet projet = new Projet();
        projet.setProjetId(record.get(PROJET.I_PROJET_ID));
        projet.setDateProjet(record.get(PROJET.D_DATE_PROJET));
        projet.setNomProjet(record.get(PROJET.V_NOM_PROJET));
        projet.setRefProjet(record.get(PROJET.V_REF_PROJET));
        projet.setPrix(record.get(PROJET.F_PRIX));
        projet.setClientProjet(RecordToClient(record));
        projet.setDevisEtat(RecordToDevisEtat(record));
        return projet;

    }

    public static Client RecordToClient(Record record) {
        Client client = new Client();
        client.setId(record.get(CLIENT.I_CLIENT_ID));
        client.setNom(record.get(CLIENT.V_NOM));
        client.setPrenom(record.get(CLIENT.V_PRENOM));
        client.setNumTel(record.get(CLIENT.V_TEL));
        client.setMail(record.get(CLIENT.V_MAIL));
        return client;
    }

    public static DevisEtat RecordToDevisEtat(Record record) {
        DevisEtat devisEtat = new DevisEtat();
        devisEtat.setDevisEtatId(record.get(DEVIS_ETAT.I_DEVIS_ETAT_ID));
        devisEtat.setDevisEtatLibelle(record.get(DEVIS_ETAT.V_DEVIS_ETAT_LIBELLE));
        devisEtat.setPourcentageSomme(record.get(DEVIS_ETAT.I_POURCENTAGE_SOMME));
        return devisEtat;
    }

    //Construct a instance of securityUser
    public static SecurityUser RecordToSecurityUser(Record record) {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(record.get(UTILISATEUR.V_LOGIN));
        securityUser.setRole(record.get(ROLE.V_LIBELLE_ROLE));
        return securityUser;
    }

    //TODO A supprimer ?
    public static ComposantReferentiel RecordToComposantReferentiel(Record record) {
        ComposantReferentiel composantReferentiel = new ComposantReferentiel();
        composantReferentiel.setComposantReferentielId(record.get(COMPOSANT_REFERENTIEL.I_COMPOSANT_REFERENTIEL_ID));
        composantReferentiel.setCaracteristiqueReferentiel(record.get(COMPOSANT_REFERENTIEL.V_CARACTERISTIQUE));
        composantReferentiel.setUniteUsage(record.get(COMPOSANT_REFERENTIEL.V_UNITE_USAGE));
        return composantReferentiel;
    }

    //TODO A supprimer ?
    public static ComposantGroupe RecordToComposantGroupe(Record record) {
        ComposantGroupe composantGroupe = new ComposantGroupe();
        composantGroupe.setComposantGroupeId(record.get(COMPOSANT_GROUPE.I_COMPOSANT_GROUPE_ID));
        composantGroupe.setLibelleGroupe(record.get(COMPOSANT_GROUPE.V_LIBELLE_GROUPE));
        return composantGroupe;
    }

    public static Composant RecordToComposant(Record record) {
        Composant composant = new Composant();
        composant.setComposantId(record.get(COMPOSANT.I_COMPOSANT_ID));
        //ComposantGroupe
        composant.setComposantGroupeId(record.get(COMPOSANT_GROUPE.I_COMPOSANT_GROUPE_ID));
        composant.setLibelleGroupe(record.get(COMPOSANT_GROUPE.V_LIBELLE_GROUPE));
        // Création de l'objet composantReferentiel
        composant.setComposantReferentielId(record.get(COMPOSANT_REFERENTIEL.I_COMPOSANT_REFERENTIEL_ID));
        composant.setCaracteristiqueReferentiel(record.get(COMPOSANT_REFERENTIEL.V_CARACTERISTIQUE));
        composant.setUniteUsage(record.get(COMPOSANT_REFERENTIEL.V_UNITE_USAGE));
        composant.setLibelle(record.get(COMPOSANT.V_LIBELLE));
        //Section peut être null
        composant.setSection(Optional.ofNullable(record.get(COMPOSANT.F_SECTION)));
        return composant;
    }

    public static Module RecordToModule(Record record) {
        Module module = new Module();
        module.setModuleId(record.get(MODULE.I_MODULE_ID));
        module.setGammeId(record.get(GAMMES.I_GAMMES_ID));
        module.setModuleReferentielId(record.get(MODULE_REFERENTIEL.I_MODULE_REFERENTIEL_ID));
        module.setCaracteristiqueReferentiel(record.get(MODULE_REFERENTIEL.V_CARACTERISTIQUE));
        module.setUniteUsage(record.get(MODULE_REFERENTIEL.V_UNITE_USAGE));
        module.setNom(record.get(MODULE.V_NOM));
        module.setAngle(record.get(MODULE.V_ANGLE));
        module.setNatureModule(record.get(MODULE.V_NATURE_MODULE));
        module.setModele(record.get(MODULE.B_MODELE));
        return module;
    }

    public static Gamme RecordToGamme(Record record) {
        Gamme gamme = new Gamme();
        gamme.setGammeId(record.get(GAMMES.I_GAMMES_ID));
        gamme.setLibelleGammes(record.get(GAMMES.V_LIBELLE_GAMMES));
        return gamme;
    }

    public static ModuleReferentiel RecordToModuleReferentiel(Record record) {
        ModuleReferentiel moduleReferentiel = new ModuleReferentiel();
        moduleReferentiel.setModuleReferentielId(record.get(MODULE_REFERENTIEL.I_MODULE_REFERENTIEL_ID));
        moduleReferentiel.setCaracteristiqueReferentiel(record.get(MODULE_REFERENTIEL.V_CARACTERISTIQUE));
        moduleReferentiel.setUniteUsage(record.get(MODULE_REFERENTIEL.V_UNITE_USAGE));
        return moduleReferentiel;
    }

    public static ModuleComposant RecordToModuleComposant(Record record) {
        ModuleComposant moduleComposant = new ModuleComposant();
        moduleComposant.setModuleId(record.get(MODULE_COMPOSANT.I_MODULE_ID));
        moduleComposant.setComposantId(record.get(MODULE_COMPOSANT.I_COMPOSANT_ID));
        moduleComposant.setOrdre(record.get(MODULE_COMPOSANT.I_ORDRE));
        return moduleComposant;
    }

    public static List<ModuleComposant> lRecordToModuleComposant(Record record) {
        List<ModuleComposant> listComposants = new ArrayList();
        ModuleComposant moduleComposant = new ModuleComposant();
        moduleComposant.setModuleId(record.get(MODULE_COMPOSANT.I_MODULE_ID));
        moduleComposant.setComposantId(record.get(MODULE_COMPOSANT.I_COMPOSANT_ID));
        moduleComposant.setOrdre(record.get(MODULE_COMPOSANT.I_ORDRE));
        listComposants.add(moduleComposant);
        return listComposants;
    }
}
