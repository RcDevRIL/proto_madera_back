package com.madera.api.repository;

import com.madera.api.models.User;
import com.madera.api.security.SecurityUser;
import com.madera.api.utils.Helper;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.madera.jooq.Tables.ROLE;
import static com.madera.jooq.Tables.UTILISATEUR;

/**
 * Repository Users
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-RELEASE
 */
@Repository
public class UserRepository {

    @Autowired
    DSLContext context;

    public Result<Record> checkUser(User user) {
        return context.select().from(UTILISATEUR)
                .where(UTILISATEUR.V_LOGIN.eq(user.getLogin()).and(UTILISATEUR.V_PASSWORD.eq(user.getPassword())))
                .fetch();
    }

    public void insertToken(User user, String token) {
        context.update(UTILISATEUR).set(UTILISATEUR.V_TOKEN, token).where(UTILISATEUR.V_LOGIN.eq(user.getLogin()))
                .execute();
    }

    public SecurityUser verifyTokenAndRole(DSLContext context, String token) {
        // Vérifie si le token reçu est le même en base de données
        return context.select(UTILISATEUR.V_LOGIN, ROLE.V_LIBELLE_ROLE).from(UTILISATEUR).join(ROLE)
                .on(ROLE.I_ROLE_ID.eq(UTILISATEUR.I_ROLE_ID)).where(UTILISATEUR.V_TOKEN.like(token))
                // .and(UTILISATEUR.I_ROLE_ID.notEqual(3)) TODO: trier les roles dans
                // AuthentificationFilter
                .fetchOne(Helper::RecordToSecurityUser);
    }
}
