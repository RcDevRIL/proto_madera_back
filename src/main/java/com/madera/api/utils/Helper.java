package com.madera.api.utils;

import com.madera.api.models.Client;
import com.madera.api.models.DevisEtat;
import com.madera.api.models.Projet;
import com.madera.api.repository.UserRepository;
import com.madera.api.security.SecurityUser;

import org.jooq.Record;

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

    // TODO méthode public ?
    // Construct an instance of securityUser
    public static SecurityUser RecordToSecurityUser(Record record) {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(record.get(UTILISATEUR.V_LOGIN));
        securityUser.setRole(record.get(ROLE.V_LIBELLE_ROLE));
        return securityUser;
    }
}
