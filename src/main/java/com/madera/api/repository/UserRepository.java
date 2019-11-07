package com.madera.api.repository;

import com.madera.api.models.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.madera.jooq.Tables.UTILISATEUR;

@Repository
public class UserRepository {

    @Autowired
    private DSLContext context;

    public Result<Record> checkUser(User user) {
        return context.select().from(UTILISATEUR)
                .where(UTILISATEUR.V_LOGIN.eq(user.getLogin()).and(UTILISATEUR.V_PASSWORD.eq(user.getPassword())))
                .fetch();
    }

    public void insertToken(User user, String token) {
        context.update(UTILISATEUR).set(UTILISATEUR.V_TOKEN, token).where(UTILISATEUR.V_LOGIN.eq(UTILISATEUR.V_LOGIN))
                .execute();
    }
}
