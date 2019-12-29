package com.madera.api.repository;

import com.madera.api.models.*;
import com.madera.api.utils.Helper;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.madera.jooq.Tables.*;

@Repository
public class ProjetRepository {

    private static final Logger log = LoggerFactory.getLogger(ProjetRepository.class);

    @Autowired
    DSLContext context;

    /**
     *
     * @param utilisateurId id de utilisateur
     * @return listProjet
     */
    public List<Projet> getAllProjectsByUserId(Integer utilisateurId) {
        return context
            .select(PROJET.fields())
            .from(PROJET)
            .join(PROJET_UTILISATEURS).on(PROJET_UTILISATEURS.I_PROJET_ID.eq(PROJET.I_PROJET_ID))
            .where(PROJET_UTILISATEURS.I_UTILISATEUR_ID.eq(utilisateurId))
            .fetch(Helper::recordToProjet);
    }

    /**
     *
     * @return listProduit qui sont des modèles
     */
    public List<Produit> getAllProduitModele() {
        return context
                .select(PRODUIT.fields())
                .from(PRODUIT)
                .where(PRODUIT.B_MODELE.isTrue())
                .fetch(Helper::recordToProduit);
    }

    /**
     *
     * @param utilisateurId id de l'utilisateur
     * @return listProduitModule
     */
    public List<ProduitModule> getAllProduitModuleByUserId(Integer utilisateurId) {
        return context
            .select(PRODUIT_MODULE.fields())
            .from(PRODUIT_MODULE)
            .join(PROJET_PRODUITS).on(PROJET_PRODUITS.I_PRODUIT_ID.eq(PRODUIT_MODULE.I_PRODUIT_ID))
            .join(PROJET_UTILISATEURS).on(PROJET_UTILISATEURS.I_PROJET_ID.eq(PROJET_PRODUITS.I_PROJET_ID))
            .where(PROJET_UTILISATEURS.I_UTILISATEUR_ID.eq(utilisateurId))
            .fetch(Helper::recordToProduitModule);
    }

    /**
     *
     * @return listAdresse
     */
    public List<Adresse> getAllAdresse() {
        return context
            .select(ADRESSE.fields())
            .from(ADRESSE)
            .fetch(Helper::recordToAdresse);
    }

    /**
     *
     * @param utilisateurId id de l'utilisateur
     * @return listProjetProduits(projetId, produitId)
     */
    public List<ProjetProduits> getAllProjetProduit(Integer utilisateurId) {
        return context
                .select(PROJET_PRODUITS.fields())
                .from(PROJET_PRODUITS)
                .join(PROJET_UTILISATEURS).on(PROJET_UTILISATEURS.I_PROJET_ID.eq(PROJET_PRODUITS.I_PROJET_ID))
                .where(PROJET_UTILISATEURS.I_UTILISATEUR_ID.eq(utilisateurId))
                .fetch(Helper::recordToProjetProduits);
    }

    public List<Produit> getAllProduit(Integer utilisateurId) {
        return context
                .select(PRODUIT.fields())
                .from(PRODUIT)
                .join(PROJET_PRODUITS).on(PROJET_PRODUITS.I_PRODUIT_ID.eq(PRODUIT.I_PRODUIT_ID))
                .join(PROJET_UTILISATEURS).on(PROJET_UTILISATEURS.I_PROJET_ID.eq(PROJET_PRODUITS.I_PROJET_ID))
                .where(PROJET_UTILISATEURS.I_UTILISATEUR_ID.eq(utilisateurId))
                .fetch(Helper::recordToProduit);
    }

    //TODO deleteAll

    //TODO updateAll

    /**
     *
     * @param projetWithAllInfos (projet with listProduits with listModule)
     * @return list des produitsId insérer
     */
    public List<Integer> createAll(ProjetWithAllInfos projetWithAllInfos) {
        return context.transactionResult(configuration -> {
            Integer projetId = createProjet(configuration, projetWithAllInfos.getProjet());
            Integer isUserAddProjet = addUserOnProjet(configuration, projetId, projetWithAllInfos.getListUtilisateurId());
            if(projetId != null && isUserAddProjet != null) {
                log.info("Le projet " + projetId + "a été créé.");
                return createProduitsAndModules(configuration, projetWithAllInfos.getProduitWithModule(), projetId);
            } else {
                log.info("Le projet n'a pas pu être créé");
                return null;
            }
        });
    }

    /**
     *
     * @param configuration configuration d'une transaction
     * @param projet projet
     * @return projetId
     */
    private Integer createProjet(Configuration configuration, Projet projet) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        return ctx
                .insertInto(PROJET)
                .columns(
                        PROJET.V_NOM_PROJET,
                        PROJET.V_REF_PROJET,
                        PROJET.D_DATE_PROJET,
                        PROJET.F_PRIX_TOTAL,
                        PROJET.I_CLIENT_ID,
                        PROJET.I_DEVIS_ETAT_ID
                )
                .values(
                        projet.getNomProjet(),
                        projet.getRefProjet(),
                        projet.getDateProjet(),
                        projet.getPrixTotal(),
                        projet.getClientId(),
                        projet.getDevisEtatId()
                )
                .onConflict(PROJET.V_REF_PROJET)
                .doNothing()
                .returning(PROJET.I_PROJET_ID)
                .fetchOne()
                .getValue(PROJET.I_PROJET_ID);
    }

    public Integer addUserOnProjet(Configuration configuration, Integer projetId, List<Integer> listUtilisateurId) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        var query = ctx
                .insertInto(PROJET_UTILISATEURS)
                .columns(
                        PROJET_UTILISATEURS.I_PROJET_ID,
                        PROJET_UTILISATEURS.I_UTILISATEUR_ID
                );
        listUtilisateurId.forEach((utilisateurId -> query.values(projetId, utilisateurId)));
        return query.execute();

    }

    /**
     *
     * @param configuration configuration d'une transaction
     * @param listProduitWithModule listProjetWithModule
     * @param projetId projet
     * @return listProduitId inseré
     */
    private List<Integer> createProduitsAndModules(Configuration configuration, List<ProduitWithProduitModule> listProduitWithModule, Integer projetId) {

        List<Integer> listProduitId = new ArrayList<>();
        listProduitWithModule.forEach((produitWithModule -> {
            Integer produitId = createProduit(configuration, produitWithModule.getProduit());
            if(produitId != null) {
                listProduitId.add(produitId);
                boolean isProjetProduit = createProjetProduit(configuration, projetId, produitId) != 0;
                if(isProjetProduit) {
                    produitWithModule.getListModules().forEach((produitModule ->
                            createProduitModule(configuration, produitId, produitModule)
                    ));
                    log.info("Le produit et ses modules ont bien été créés");
                } else {
                    log.info("Erreur lors de l'insertion dans projet_produit");
                }
            } else {
                log.info("Le produit n'a pas pu être créé");
            }
        }));
        return listProduitId;
    }

    /**
     *
     * @param configuration configuration d'une transaction
     * @param produit produit
     * @return produitId
     */
    private Integer createProduit(Configuration configuration, Produit produit) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        return ctx
                .insertInto(PRODUIT)
                .columns(
                        PRODUIT.V_PRODUIT_NOM,
                        PRODUIT.I_GAMMES_ID,
                        PRODUIT.F_PRIX_PRODUIT,
                        PRODUIT.B_MODELE
                )
                .values(
                        produit.getProduitNom(),
                        produit.getGammesId(),
                        produit.getPrixProduit(),
                        produit.getModele()
                )
                .returning(PRODUIT.I_PRODUIT_ID)
                .fetchOne()
                .getValue(PRODUIT.I_PRODUIT_ID);
    }

    /**
     *
     * @param configuration configuration d'une transaction
     * @param projetId projet
     * @param produitId produit
     * @return Integer, 0 si aucun insert
     */
    private Integer createProjetProduit(Configuration configuration, Integer projetId, Integer produitId) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        return ctx
                .insertInto(PROJET_PRODUITS)
                .columns(
                        PROJET_PRODUITS.I_PROJET_ID,
                        PROJET_PRODUITS.I_PRODUIT_ID
                )
                .values(projetId, produitId)
                .execute();
    }

    /**
     *
     * @param configuration configuration d'une transaction
     * @param produitId produit
     * @param produitModule produitModule
     */
    private void createProduitModule(Configuration configuration, Integer produitId, ProduitModule produitModule) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        ctx.insertInto(PRODUIT_MODULE)
                .columns(
                        PRODUIT_MODULE.I_PRODUIT_ID,
                        PRODUIT_MODULE.I_MODULE_ID,
                        PRODUIT_MODULE.V_PRODUIT_MODULE_NOM,
                        PRODUIT_MODULE.V_PRODUIT_MODULE_ANGLE,
                        PRODUIT_MODULE.J_SECTION_LONGUEUR
                )
                .values(
                        produitId,
                        produitModule.getModuleId(),
                        produitModule.getProduitModuleNom(),
                        produitModule.getProduitModuleAngle(),
                        JSONB.valueOf(produitModule.getProduitModuleSectionLongueur())
                        )
                .execute();
    }

    //TODO upsert ?

    //TODO deleteProduits

    //TODO deleteProjet

    //TODO updateProjet

    //TODO updateProduit

    //TODO updateProjetProduit

    //TODO updateProduitModule

    //TODO list produits, listModule,
    /*public Integer createProjet(
            Projet projet,
            List<ProduitModule> listProduitModule,
            Integer utilisateurId
    ) {
        return context.transactionResult(configuration-> {
            Record record =
                DSL.using(configuration).insertInto(
                    PROJET)
                    .set(PROJET.V_NOM_PROJET,projet.nomProjet)
                    .set(PROJET.V_REF_PROJET, projet.refProjet)
                    .set(PROJET.D_DATE_PROJET, projet.dateProjet)
                    .set(PROJET.F_PRIX_TOTAL, projet.prix)
                    .set(PROJET.I_CLIENT_ID, projet.clientId)
                    .set(PROJET.I_DEVIS_ETAT_ID, projet.devisEtatId)
                .returning(PROJET.I_PROJET_ID)
                .fetchOne();

            //Insert into produits

            //Insert into produits projet
            //Factoring

            var query = context
                    .insertInto(PRODUIT_MODULE)
                    .columns(PRODUIT_MODULE.I_MODULE_ID, PRODUIT_MODULE.I_PRODUIT_ID);
            listProduitModule.forEach((produitModule -> {
                query.values(produitModule.getModuleId(), record.get(PROJET.I_PROJET_ID));
            }));
            query.execute();

            DSL.using(configuration).insertInto(PROJET_UTILISATEURS)
                    .set(PROJET_UTILISATEURS.I_PROJET_ID, record.get(PROJET.I_PROJET_ID))
                    .set(PROJET_UTILISATEURS.I_UTILISATEUR_ID, utilisateurId)
                    .execute();
            return record.get(PROJET.I_PROJET_ID);
        });
    }*/

    /*public Integer updateProject(Projet projet) {
        return context
                .update(PROJET)
                .set(PROJET.V_NOM_PROJET, projet.getNomProjet())
                .set(PROJET.V_REF_PROJET, projet.getRefProjet())
                .set(PROJET.D_DATE_PROJET, projet.getDateProjet())
                .set(PROJET.F_PRIX_TOTAL, projet.getPrix())
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
                    .delete(PRODUIT_MODULE)
                    .where(PRODUIT_MODULE.I_PROJET_ID.eq(record.get(PROJET.I_PROJET_ID)))
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
    }*/
}
