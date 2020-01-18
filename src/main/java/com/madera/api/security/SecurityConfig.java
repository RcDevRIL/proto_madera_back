package com.madera.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * Classe permettant de configurer l'authentification
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 1.0-RELEASE
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
                //adding a cors configuration using by default corsConfigurationSource
                .cors().and()
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration conf = new CorsConfiguration();
        conf.setAllowedHeaders(Collections.singletonList("*"));
        conf.setAllowedMethods(Collections.singletonList("*"));
        conf.setMaxAge((long) 3600);
        conf.setAllowCredentials(true);
        //conf.setExposedHeaders();
        conf.setAllowedOrigins(Arrays.asList("https://rcdevril.github.io/proto_madera_front/", "https://proto_madera_front.codemagic.app/"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);
        return source;
    }
}
