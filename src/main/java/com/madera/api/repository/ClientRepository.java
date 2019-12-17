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

    public void addClientAdresse(List<ClientAdresse> listClientAdresse) {
        context.transaction(configuration -> {
            listClientAdresse.forEach(clientAdresse -> {
                    DSL.using(configuration)
                            .insertInto(CLIENT_ADRESSE)
                            .set(CLIENT_ADRESSE.I_CLIENT_ID, clientAdresse.getClientId())
                            .set(CLIENT_ADRESSE.I_ADRESSE_ID, clientAdresse.getAdresseId())
                            .set(CLIENT_ADRESSE.B_ADRESSE_FACTURATION, clientAdresse.getAdresseFacturation())
                            .execute();
            });
        });
    }

    public void createAdresse(List<Adresse> listAdresse) {
        listAdresse.forEach(adresse -> {
            context
                    .insertInto(ADRESSE)
                    .set(ADRESSE.V_VILLE, adresse.getVille())
                    .set(ADRESSE.V_CODE_POSTALE, adresse.getCodePostale())
                    .set(ADRESSE.V_RUE, adresse.getRue())
                    .set(ADRESSE.V_COMPLEMENT, adresse.getComplement())
                    .set(ADRESSE.V_NUMERO, adresse.getNumero())
                    .execute();
        });
    }
}
