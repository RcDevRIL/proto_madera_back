package com.madera.utils;

import com.madera.api.models.Client;
import com.madera.api.models.DevisEtat;
import com.madera.api.models.Projet;
import org.jooq.Record;

import static com.madera.jooq.Tables.PROJET;
import static com.madera.jooq.tables.Client.CLIENT;
import static com.madera.jooq.tables.DevisEtat.DEVIS_ETAT;

public class HelperBuild {

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

    private static Client RecordToClient(Record record) {
        Client client = new Client();
        client.setId(record.get(CLIENT.I_CLIENT_ID));
        client.setNom(record.get(CLIENT.V_NOM));
        client.setPrenom(record.get(CLIENT.V_PRENOM));
        client.setNumTel(record.get(CLIENT.V_TEL));
        client.setMail(record.get(CLIENT.V_MAIL));
        return client;
    }

    private static DevisEtat RecordToDevisEtat(Record record) {
        DevisEtat devisEtat = new DevisEtat();
        devisEtat.setDevisEtatId(record.get(DEVIS_ETAT.I_DEVIS_ETAT_ID));
        devisEtat.setDevisEtatLibelle(record.get(DEVIS_ETAT.V_DEVIS_ETAT_LIBELLE));
        devisEtat.setPourcentageSomme(record.get(DEVIS_ETAT.I_POURCENTAGE_SOMME));
        return devisEtat;
    }
}
