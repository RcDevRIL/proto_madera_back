package com.madera.api.models;

/**
 * DTO Adresse
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.2-PRE-RELEASE
 */
public class Adresse {

    Integer adresseId;

    String ville;

    String codePostale;

    String rue;

    String complement;

    String numero;

    public Adresse() {
    }

    public Adresse(String ville, String codePostale, String rue, String complement, String numero) {
        this.ville = ville;
        this.codePostale = codePostale;
        this.rue = rue;
        this.complement = complement;
        this.numero = numero;
    }

    public Integer getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Integer adresseId) {
        this.adresseId = adresseId;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
