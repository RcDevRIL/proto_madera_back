package com.madera.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:madera.properties")
class DatasourceConfig {

    @Value("${driver.className}")
    private String driverClassName;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.pass}")
    private String dbPass;

    @Bean
    public DataSource datasource() {
        return DataSourceBuilder.create().driverClassName(driverClassName).url(dbUrl).username(dbUser).password(dbPass)
                .build();
    }
}