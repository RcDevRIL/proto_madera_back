package com.madera.api.repository;

import com.madera.api.models.Adresse;
import com.madera.api.models.Client;
import com.madera.api.models.ClientAdresse;
import com.madera.api.utils.Helper;
import org.apache.commons.compress.archivers.sevenz.CLI;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.madera.jooq.Tables.*;

/**
 * Repository Client
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 1.0-PRE-RELEASE
 */
@Repository
public class ClientRepository {

        @Autowired
        DSLContext context;

        /**
         * Méthode pour récupérer la liste des clients
         * 
         * @return listClient
         */
        public List<Client> getAllClient() {
                return context.select(CLIENT.fields()).from(CLIENT).fetch(Helper::recordToClient);
        }

        /**
         * Méthode récupérant un client
         * 
         * @param nom    du client
         * @param prenom du client
         * @return client
         */
        public Client getClientByNomAndPrenom(String nom, String prenom) {
                return context.select(CLIENT.fields()).from(CLIENT)
                                .where(CLIENT.V_NOM.like(nom).and(CLIENT.V_PRENOM.like(prenom)))
                                .fetchOne(Helper::recordToClient);
        }

        /**
         * Méthode création d'un client
         * 
         * @param client client
         * @return clientId
         */
        public Integer createClient(Client client) {
                Record record = context.insertInto(CLIENT).set(CLIENT.V_NOM, client.nom)
                                .set(CLIENT.V_PRENOM, client.prenom).set(CLIENT.V_MAIL, client.mail)
                                .set(CLIENT.V_TEL, client.numTel).returning(CLIENT.I_CLIENT_ID).fetchOne();
                return record.get(CLIENT.I_CLIENT_ID);
        }

        /**
         * Méthode de mise à jour d'un client
         * 
         * @param client client
         * @return != 0 si il a été mis à jour
         */
        public Integer updateClient(Client client) {
                return context.update(CLIENT).set(CLIENT.V_NOM, client.getNom())
                                .set(CLIENT.V_PRENOM, client.getPrenom()).set(CLIENT.V_MAIL, client.getMail())
                                .set(CLIENT.V_TEL, client.getNumTel()).where(CLIENT.I_CLIENT_ID.eq(client.getId()))
                                .execute();
        }

        // Supprime le client
        // TODO Enlever contrainte étrangère clientId sur projet

        /**
         * Méthode suppression du client
         * 
         * @param clientId du client
         * @return != 0 s'il a été supprimé
         */
        public Integer deleteClient(Integer clientId) {
                return context.transactionResult(configuration -> {
                        Integer linesDeleted = DSL.using(configuration).delete(CLIENT_ADRESSE)
                                        .where(CLIENT_ADRESSE.I_CLIENT_ID.eq(clientId)).execute();
                        linesDeleted = linesDeleted + DSL.using(configuration).delete(CLIENT)
                                        .where(CLIENT.I_CLIENT_ID.eq(clientId)).execute();
                        return linesDeleted;
                });
        }

        /**
         * Méthode qui récupère la liste des clientAdresse
         * 
         * @return listClientAdresse
         */
        public List<ClientAdresse> getAllClientAdresse() {
                return context.select(CLIENT_ADRESSE.fields()).from(CLIENT_ADRESSE)
                                .fetch(Helper::recordToClientAdresse);
        }

        /**
         * Méthode pour ajouter un clientAdresse
         * 
         * @param listClientAdresse listClientAdresse
         * @return != 0 si les clientAdresse ont été ajoutés.
         */
        public Integer addClientAdresse(List<ClientAdresse> listClientAdresse) {
                var query = context.insertInto(CLIENT_ADRESSE).columns(CLIENT_ADRESSE.I_CLIENT_ID,
                                CLIENT_ADRESSE.I_ADRESSE_ID, CLIENT_ADRESSE.B_ADRESSE_FACTURATION);
                listClientAdresse.forEach(clientAdresse -> query.values(clientAdresse.getClientId(),
                                clientAdresse.getAdresseId(), clientAdresse.getAdresseFacturation()));
                return query.execute();
        }

        /**
         * Méthode de création d'une adresse
         * 
         * @param listAdresse listAdresse
         * @return != 0 si les adresses ont été ajoutées.
         */
        public Integer createAdresse(List<Adresse> listAdresse) {
                var query = context.insertInto(ADRESSE).columns(ADRESSE.V_VILLE, ADRESSE.V_CODE_POSTALE, ADRESSE.V_RUE,
                                ADRESSE.V_COMPLEMENT, ADRESSE.V_NUMERO);
                listAdresse.forEach(adresse -> query.values(adresse.getVille(), adresse.getCodePostale(),
                                adresse.getRue(), adresse.getComplement(), adresse.getNumero()));
                return query.execute();
        }

        public Client getClientByProjetId(Integer projetId) {
                return context.select(CLIENT.fields()).from(CLIENT).join(PROJET)
                                .on(PROJET.I_CLIENT_ID.eq(CLIENT.I_CLIENT_ID)).where(PROJET.I_PROJET_ID.eq(projetId))
                                .fetchOne(Helper::recordToClient);
        }

        public Adresse getAdresseByClientId(Integer clientId) {
                return context.select(ADRESSE.fields()).from(ADRESSE).join(CLIENT_ADRESSE)
                                .on(CLIENT_ADRESSE.I_ADRESSE_ID.eq(ADRESSE.I_ADRESSE_ID))
                                .where(CLIENT_ADRESSE.I_CLIENT_ID.eq(clientId)
                                                .and(CLIENT_ADRESSE.B_ADRESSE_FACTURATION.isTrue()))
                                .fetchOne(Helper::recordToAdresse);
        }
}
