package com.madera.api.repository;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.madera.jooq.tables.Composant.COMPOSANT;

@Repository
public class ComposantRepository {

    @Autowired
    DSLContext context;

    public Result<Record> getAllComposant() {
        return context.select().from(COMPOSANT).fetch();
    }
}
