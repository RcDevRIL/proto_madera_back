package com.madera.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe permettant de configurer l'authentification
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 1.0-PRE-RELEASE
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthentificationEntryPoint jwtAuthentificationEntryPoint;

    @Autowired
    private AuthentificationFilter authentificationFilter;

    /**
     * Configure les accès aux différentes routes de l'application
     * 
     * @param http HttpSecurity
     * @throws Exception exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // We don't need CSRF for this example
        http.csrf().disable().httpBasic().disable().formLogin().disable()
                // dont authenticat this particular requests
                .authorizeRequests().antMatchers("/api/authentification", "/api").permitAll().
                // all other requests need to be authenticated
                anyRequest().authenticated().and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().authenticationEntryPoint(jwtAuthentificationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(authentificationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
