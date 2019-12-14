package com.madera.api.repository;

import com.madera.api.models.*;
import com.madera.api.utils.Helper;
import com.madera.jooq.tables.records.ProjetRecord;
import com.madera.jooq.tables.records.UtilisateurRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.madera.jooq.Tables.*;

/**
 * Repository Projet
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.2-PRE-RELEASE
 */
@Repository
public class ProjetRepository {

    @Autowired
    DSLContext context;

    public List<Projet> getAllProjectsByUserId(Integer utilisateurId) {
        return context.select(PROJET.fields()).from(PROJET).join(PROJET_UTILISATEURS)
                .on(PROJET_UTILISATEURS.I_PROJET_ID.eq(PROJET.I_PROJET_ID))
                .where(PROJET_UTILISATEURS.I_UTILISATEUR_ID.eq(utilisateurId)).fetch(Helper::RecordToProjet);
    }

    public List<ProjetModule> getAllProjectModuleByUserId(Integer utilisateurId) {
        return context.select(PROJET_MODULE.fields()).from(PROJET_MODULE).join(PROJET_UTILISATEURS)
                .on(PROJET_UTILISATEURS.I_PROJET_ID.eq(PROJET_MODULE.I_PROJET_ID))
                .where(PROJET_UTILISATEURS.I_UTILISATEUR_ID.eq(utilisateurId)).fetch(Helper::RecordToProjetModule);
    }

    public List<Client> getAllClient() {
        return context.select(CLIENT.fields()).from(CLIENT).fetch(Helper::RecordToClient);
    }

    public List<ClientAdresse> getAllClientAdresse() {
        return context.select(CLIENT_ADRESSE.fields()).from(CLIENT_ADRESSE).fetch(Helper::RecordToClientAdresse);
    }

    public List<Adresse> getAllAdresse() {
        return context.select(ADRESSE.fields()).from(ADRESSE).fetch(Helper::RecordToAdresse);
    }

    public Integer createProjet(Projet projet, List<ProjetModule> listProjetModule, Integer utilisateurId) {
        return context.transactionResult(configuration -> {
            Record record = DSL.using(configuration).insertInto(PROJET).set(PROJET.V_NOM_PROJET, projet.nomProjet)
                    .set(PROJET.V_REF_PROJET, projet.refProjet).set(PROJET.D_DATE_PROJET, projet.dateProjet)
                    .set(PROJET.F_PRIX, projet.prix).set(PROJET.I_CLIENT_ID, projet.clientId)
                    .set(PROJET.I_DEVIS_ETAT_ID, projet.devisEtatId).returning(PROJET.I_PROJET_ID).fetchOne();

            listProjetModule.forEach((projetModule -> {
                DSL.using(configuration).insertInto(PROJET_MODULE)
                        .set(PROJET_MODULE.I_MODULE_ID, projetModule.getModuleId())
                        .set(PROJET_MODULE.I_PROJET_ID, record.get(PROJET.I_PROJET_ID)).execute();
            }));

            DSL.using(configuration).insertInto(PROJET_UTILISATEURS)
                    .set(PROJET_UTILISATEURS.I_PROJET_ID, record.get(PROJET.I_PROJET_ID))
                    .set(PROJET_UTILISATEURS.I_UTILISATEUR_ID, utilisateurId).execute();
            return record.get(PROJET.I_PROJET_ID);
        });
    }
}
