package com.madera.api.repository;

import com.madera.api.models.*;
import com.madera.api.utils.Helper;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.madera.jooq.Tables.*;

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

    public List<Client> getAllClient() {
        return context
            .select(CLIENT.fields())
            .from(CLIENT)
            .fetch(Helper::RecordToClient);
    }

    public List<ClientAdresse> getAllClientAdresse() {
        return context
            .select(CLIENT_ADRESSE.fields())
            .from(CLIENT_ADRESSE)
            .fetch(Helper::RecordToClientAdresse);
    }

    public List<Adresse> getAllAdresse() {
        return context
            .select(ADRESSE.fields())
            .from(ADRESSE)
            .fetch(Helper::RecordToAdresse);
    }
}
