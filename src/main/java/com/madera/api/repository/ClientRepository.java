package com.madera.api.repository;

import com.madera.api.models.Adresse;
import com.madera.api.models.Client;
import com.madera.api.models.ClientAdresse;
import com.madera.api.utils.Helper;
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
 * @version 0.1-RELEASE
 */
@Repository
public class ClientRepository {

    @Autowired
    DSLContext context;

    public List<Client> getAllClient() {
        return context
                .select(CLIENT.fields())
                .from(CLIENT)
                .fetch(Helper::RecordToClient);
    }

    public Client getClientByNomAndPrenom(String nom, String prenom) {
        return context
                .select(CLIENT.fields())
                .from(CLIENT)
                .where(CLIENT.V_NOM.like(nom).and(CLIENT.V_PRENOM.like(prenom)))
                .fetchOne(Helper::RecordToClient);
    }

    public Integer createClient(Client client) {
        Record record = context
                .insertInto(CLIENT)
                .set(CLIENT.V_NOM, client.nom)
                .set(CLIENT.V_PRENOM, client.prenom)
                .set(CLIENT.V_MAIL, client.mail)
                .set(CLIENT.V_TEL, client.numTel)
                .returning(CLIENT.I_CLIENT_ID)
                .fetchOne();
        return record.get(CLIENT.I_CLIENT_ID);
    }

    public Integer updateClient(Client client) {
        return context
                .update(CLIENT)
                .set(CLIENT.V_NOM, client.getNom())
                .set(CLIENT.V_PRENOM, client.getPrenom())
                .set(CLIENT.V_MAIL, client.getMail())
                .set(CLIENT.V_TEL, client.getNumTel())
                .where(CLIENT.I_CLIENT_ID.eq(client.getId()))
                .execute();
    }

    //Supprime le client
    //TODO Enlever contrainte étrangère clientId sur projet
    public Integer deleteClient(Integer clientId) {
        return context.transactionResult(configuration -> {
            Integer linesDeleted = null;
            linesDeleted = DSL.using(configuration)
                    .delete(CLIENT_ADRESSE)
                    .where(CLIENT_ADRESSE.I_CLIENT_ID.eq(clientId))
                    .execute();
            linesDeleted = linesDeleted + DSL.using(configuration)
                    .delete(CLIENT)
                    .where(CLIENT.I_CLIENT_ID.eq(clientId))
                    .execute();
            return linesDeleted;
        });
    }

    public List<ClientAdresse> getAllClientAdresse() {
        return context
                .select(CLIENT_ADRESSE.fields())
                .from(CLIENT_ADRESSE)
                .fetch(Helper::RecordToClientAdresse);
    }

    public Integer addClientAdresse(List<ClientAdresse> listClientAdresse) {
        var query = context
                .insertInto(CLIENT_ADRESSE)
                .columns(CLIENT_ADRESSE.I_CLIENT_ID,
                        CLIENT_ADRESSE.I_ADRESSE_ID,
                        CLIENT_ADRESSE.B_ADRESSE_FACTURATION
                );
        listClientAdresse.forEach(clientAdresse -> {
            query.values(
                    clientAdresse.getClientId(),
                    clientAdresse.getAdresseId(),
                    clientAdresse.getAdresseFacturation()
            );
        });
        return query.execute();
    }

    public Integer createAdresse(List<Adresse> listAdresse) {
        var query = context
                .insertInto(ADRESSE)
                .columns(ADRESSE.V_VILLE,
                        ADRESSE.V_CODE_POSTALE,
                        ADRESSE.V_RUE,
                        ADRESSE.V_COMPLEMENT,
                        ADRESSE.V_NUMERO
                );
        listAdresse.forEach(adresse -> {
            query.values(
                    adresse.getVille(),
                    adresse.getCodePostale(),
                    adresse.getRue(),
                    adresse.getComplement(),
                    adresse.getNumero());
        });
        return query.execute();
    }
}
