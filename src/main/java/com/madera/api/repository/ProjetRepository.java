package com.madera.api.repository;

import com.madera.api.models.Adresse;
import com.madera.api.models.Projet;
import com.madera.api.models.ProjetModule;
import com.madera.api.utils.Helper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.madera.jooq.Tables.*;
import static org.jooq.impl.DSL.delete;

@Repository
public class ProjetRepository {

    @Autowired
    DSLContext context;

    public List<Projet> getAllProjectsByUserId(Integer utilisateurId) {
        return context
            .select(PROJET.fields())
            .from(PROJET)
            .join(PROJET_UTILISATEURS).on(PROJET_UTILISATEURS.I_PROJET_ID.eq(PROJET.I_PROJET_ID))
            .where(PROJET_UTILISATEURS.I_UTILISATEUR_ID.eq(utilisateurId))
            .fetch(Helper::RecordToProjet);
    }

    public List<ProjetModule> getAllProjectModuleByUserId(Integer utilisateurId) {
        return context
            .select(PROJET_MODULE.fields())
            .from(PROJET_MODULE)
            .join(PROJET_UTILISATEURS).on(PROJET_UTILISATEURS.I_PROJET_ID.eq(PROJET_MODULE.I_PROJET_ID))
            .where(PROJET_UTILISATEURS.I_UTILISATEUR_ID.eq(utilisateurId))
            .fetch(Helper::RecordToProjetModule);
    }

    public List<Adresse> getAllAdresse() {
        return context
            .select(ADRESSE.fields())
            .from(ADRESSE)
            .fetch(Helper::RecordToAdresse);
    }

    public Integer createProjet(Projet projet, List<ProjetModule> listProjetModule, Integer utilisateurId) {
        return context.transactionResult(configuration-> {
            Record record =
                DSL.using(configuration).insertInto(
                    PROJET)
                    .set(PROJET.V_NOM_PROJET,projet.nomProjet)
                    .set(PROJET.V_REF_PROJET, projet.refProjet)
                    .set(PROJET.D_DATE_PROJET, projet.dateProjet)
                    .set(PROJET.F_PRIX, projet.prix)
                    .set(PROJET.I_CLIENT_ID, projet.clientId)
                    .set(PROJET.I_DEVIS_ETAT_ID, projet.devisEtatId)
                .returning(PROJET.I_PROJET_ID)
                .fetchOne();

            var query = context
                    .insertInto(PROJET_MODULE)
                    .columns(PROJET_MODULE.I_MODULE_ID, PROJET_MODULE.I_PROJET_ID);
            listProjetModule.forEach((projetModule -> {
                query.values(projetModule.getModuleId(), record.get(PROJET.I_PROJET_ID));
            }));
            query.execute();

            DSL.using(configuration).insertInto(PROJET_UTILISATEURS)
                    .set(PROJET_UTILISATEURS.I_PROJET_ID, record.get(PROJET.I_PROJET_ID))
                    .set(PROJET_UTILISATEURS.I_UTILISATEUR_ID, utilisateurId)
                    .execute();
            return record.get(PROJET.I_PROJET_ID);
        });
    }

    public Integer updateProject(Projet projet) {
        return context
                .update(PROJET)
                .set(PROJET.V_NOM_PROJET, projet.getNomProjet())
                .set(PROJET.V_REF_PROJET, projet.getRefProjet())
                .set(PROJET.D_DATE_PROJET, projet.getDateProjet())
                .set(PROJET.F_PRIX, projet.getPrix())
                .set(PROJET.I_DEVIS_ETAT_ID, projet.getDevisEtatId())
                .where(PROJET.V_REF_PROJET.eq(projet.getRefProjet()))
                .execute();
    }

    public Integer deleteProject(String refProjet) {
        //Récupére le projetId suivant le refProjet passé en paramètre
        Record record = context
                .select(PROJET.I_PROJET_ID)
                .from(PROJET)
                .where(PROJET.V_REF_PROJET.eq(refProjet))
                .fetchOne();
        System.out.println(record.get(PROJET.I_PROJET_ID));
        return context.transactionResult(configuration -> {
            Integer linesDeleted = null;
            //Delete des projets_modules
            linesDeleted = DSL.using(configuration)
                    .delete(PROJET_MODULE)
                    .where(PROJET_MODULE.I_PROJET_ID.eq(record.get(PROJET.I_PROJET_ID)))
                    .execute();
            //Delete des projets_utilisateurs
            linesDeleted = linesDeleted + DSL.using(configuration)
                    .delete(PROJET_UTILISATEURS)
                    .where(PROJET_UTILISATEURS.I_PROJET_ID.eq(record.get(PROJET.I_PROJET_ID)))
                    .execute();
            //Delete du projet
            linesDeleted = linesDeleted + DSL.using(configuration)
                    .delete(PROJET)
                    .where(PROJET.V_REF_PROJET.eq(refProjet))
                    .execute();
            return linesDeleted;
        });
    }
}
