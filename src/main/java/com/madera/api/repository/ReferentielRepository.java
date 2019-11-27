package com.madera.api.repository;

import com.madera.api.models.Composant;
import com.madera.api.models.Gamme;
import com.madera.api.models.Projet;
import com.madera.utils.HelperBuild;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.madera.jooq.Tables.*;

@Repository
public class ReferentielRepository {

    @Autowired
    DSLContext context;

    //TODO Faire une seul méthode pour renvoyer les données du référentiel ? Je pense fortement
    //TODO Ca évitera de surcharger le controller avec des imports dans tous les sens
    public List<Composant> getAllComposant() {
        return context.select(COMPOSANT.fields()).from(COMPOSANT).fetchInto(Composant.class);
    }

    public List<Gamme> getAllGammes() {
        return context.select(GAMMES.fields()).from(GAMMES).fetchInto(Gamme.class);
    }

    public List<Projet> getAllProjects(Integer userId) {
        return context
            .select(PROJET.fields())
            .select(CLIENT.fields())
            .select(DEVIS_ETAT.fields())
            .from(PROJET)
            .join(CLIENT)
            .using(CLIENT.I_CLIENT_ID)
            .join(DEVIS_ETAT)
            .using(DEVIS_ETAT.I_DEVIS_ETAT_ID)
            .join(PROJET_UTILISATEURS)
            .on(PROJET_UTILISATEURS.I_PROJET_ID.eq(userId))
            .fetch(HelperBuild::RecordToProject);
    }
}
