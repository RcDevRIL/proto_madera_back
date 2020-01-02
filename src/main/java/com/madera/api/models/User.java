package com.madera.api.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * DTO User
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.3-RELEASE
 */
@JsonSerialize
@JsonDeserialize
public class User {

    Integer utilisateurId;

    String login;

    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer userId) {
        this.utilisateurId = userId;
    }

    public User() {
    }

    public User(Integer userId, String login, String token) {
        this.utilisateurId = userId;
        this.login = login;
        this.token = token;
    }

    @Override
    public String toString() {
        return "User: " + this.login + ", password: " + this.token;
    }
}
