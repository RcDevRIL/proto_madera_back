package com.madera.api.repository;

import com.madera.api.models.*;
import com.madera.api.utils.Helper;
import com.madera.jooq.tables.records.ProduitRecord;
import com.madera.jooq.tables.records.ProjetProduitsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.DeleteWhereStep;
import org.jooq.JSONB;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.madera.jooq.Tables.*;
import static org.jooq.impl.DSL.select;

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
        //TODO Il semblerais avoir une erreur lorsque le onConflictDoNothing survient, aucun élément n'est renvoyer
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

    /**
     * Supprime tout ce qui est en relation avec la refProjet dont le projet lui même
     * @param refProjet reference du projet en question
     * @return un integer
     */
    public Integer deleteAll(String refProjet) {
        //L'ordre est important
        return context.transactionResult(configuration -> {
            List<Integer> listProduitId = deleteProjetProduitByRefProjet(configuration, refProjet);

            listProduitId.forEach((produitId) -> {
                deleteProduitModuleByProduitId(configuration, produitId);
                deleteProduitByProduitId(configuration, produitId);
            }
            );
            deleteProjetUtilisateurByRefProjet(configuration, refProjet);
            return deleteProjetByRefProjet(configuration, refProjet);
        });
    }

    /**
     * Supprime les utilisateurs liés au projet en fonction de la refprojet passé en paramètre
     * @param configuration configuration d'une transaction
     * @param refProjet reference du projet
     */
    private void deleteProjetUtilisateurByRefProjet(Configuration configuration, String refProjet) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        ctx
                .delete(PROJET_UTILISATEURS)
                .where(PROJET_UTILISATEURS.I_PROJET_ID.in(
                        select(PROJET.I_PROJET_ID)
                                .from(PROJET)
                                .where(PROJET.V_REF_PROJET.eq(refProjet)))
                )
                .execute();
    }

    /**
     * Supprime le projet suivant le refProjet passé en paramètre
     * @param configuration configuration d'une transaction
     * @param refProjet reference du projet
     */
    private Integer deleteProjetByRefProjet(Configuration configuration, String refProjet) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        return ctx
                .delete(PROJET)
                .where(PROJET.V_REF_PROJET.eq(refProjet))
                .execute();
    }

    private DeleteWhereStep<ProjetProduitsRecord> deleteProjetProduitQuery(Configuration configuration) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        return ctx
                .delete(PROJET_PRODUITS);
    }

    /**
     * Delete de projetProduit by refProjet
     * @param configuration configuration d'une transaction
     * @param refProjet reference du projet
     */
    private List<Integer> deleteProjetProduitByRefProjet(Configuration configuration, String refProjet) {
        return deleteProjetProduitQuery(configuration)
                .where(PROJET_PRODUITS.I_PROJET_ID.in(
                        select(PROJET.I_PROJET_ID)
                                .from(PROJET)
                                .where(PROJET.V_REF_PROJET.eq(refProjet))
                        )
                )
                .returning(PROJET_PRODUITS.I_PRODUIT_ID)
                .fetch()
                .getValues(PROJET_PRODUITS.I_PRODUIT_ID);
    }

    /**
     * Supprime un produit
     * @param configuration configuration d'une transaction
     * @param produitId produitId
     * @return != 0 si des éléments ont été supprimés
     */
    public Integer deleteProduit(Configuration configuration, Integer produitId) {
        Integer isDeleted = deleteProjetProduitByProduitId(configuration, produitId);
        if(isDeleted != 0) {
            //Si au moins une ligne a été supprimée on continue
            deleteProduitByProduitId(configuration, produitId);
        }
        return isDeleted;
    }

    /**
     * Delete de projetProduit by produitId
     * @param configuration configuration d'une transaction
     * @param produitId Integer
     * @return un integer suivant si le projet_produits a été supprimé.
     */
    private Integer deleteProjetProduitByProduitId(Configuration configuration, Integer produitId) {
        return deleteProjetProduitQuery(configuration)
                .where(PROJET_PRODUITS.I_PRODUIT_ID.eq(produitId))
                .execute();
    }

    /**
     *
     * @param configuration configuration d'une transaction
     * @return query deleteProduit
     */
    private DeleteWhereStep<ProduitRecord> deleteProduitQuery(Configuration configuration) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        return ctx
                .delete(PRODUIT);
    }

    /**
     * Supprime tous les produits d'un projet
     * @param configuration configuration d'une transaction
     * @param refProjet reference d'un projet
     * @return un integer suivant si le ou les produits ont été supprimés.
     */
    public void deleteProduitByRefProjet(Configuration configuration, String refProjet) {
        deleteProduitQuery(configuration)
                .where(PRODUIT.I_PRODUIT_ID.in(
                        select(PROJET_PRODUITS.I_PRODUIT_ID)
                                .from(PROJET_PRODUITS)
                                .join(PROJET).on(PROJET.I_PROJET_ID.eq(PROJET_PRODUITS.I_PROJET_ID))
                                .where(PROJET.V_REF_PROJET.eq(refProjet)))
                        .and(PRODUIT.B_MODELE.isFalse())
                )
                .execute();
    }

    /**
     * Supprime un produit spécifique, il faut supprimer l'occurence dans projet_produits si le produitId est présent avant
     * de supprimer dans la table produit.
     * @param configuration configuration d'une transaction
     * @param produitId produitId
     * @return un integer suivant si le produit a été supprimé.
     */
    public void deleteProduitByProduitId(Configuration configuration, Integer produitId) {
        deleteProduitQuery(configuration)
                .where(PRODUIT.I_PRODUIT_ID.eq(produitId))
                .execute();
    }

    public void deleteProduitModuleByProduitId(Configuration configuration, Integer produitId) {
        //Initialise le ctx selon celui en est cours (context ou transaction)
        DSLContext ctx = configuration == null ? context : DSL.using(configuration);
        ctx
                .delete(PRODUIT_MODULE)
                .where(PRODUIT_MODULE.I_PRODUIT_ID.eq(produitId))
                .execute();
    }

    //TODO updateProjet ? uppdateProduit ?
    public void updateAll() {

    }
    //TODO updateProjet

    //TODO updateProduit

    //TODO updateProjetProduit

    //TODO updateProduitModule

    //TODO list produits, listModule,
}
