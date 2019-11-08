package com.madera.api.repository;

import com.madera.api.models.Composant;
import com.madera.api.models.Gamme;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.madera.jooq.Tables.GAMMES;
import static com.madera.jooq.Tables.COMPOSANT;

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
}
