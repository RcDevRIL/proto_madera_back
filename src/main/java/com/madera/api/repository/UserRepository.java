package com.madera.api.repository;

import com.madera.api.models.User;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

import static com.madera.jooq.Tables.UTILISATEUR;

@Service
public class UserRepository {

    @Autowired
    private DSLContext context;

    public Result checkUser(User user) {
        return context
            .select()
            .from(UTILISATEUR)
            .where(UTILISATEUR.V_LOGIN.eq(user.getLogin())
                    .and(UTILISATEUR.V_PASSWORD.eq(user.getPassword()))
            )
            .fetch();
    }

    public void insertToken(User user, String token) {
        context
            .update(UTILISATEUR)
            .set(UTILISATEUR.V_TOKEN, token)
            .where(UTILISATEUR.V_LOGIN.eq(UTILISATEUR.V_LOGIN))
            .execute();
    }
}
