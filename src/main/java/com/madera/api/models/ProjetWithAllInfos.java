package com.madera.api.models;

import java.util.List;

public class ProjetWithAllInfos {
    Projet projet;

    List<ProduitWithProduitModule> produitWithModule;

    List<Integer> listUtilisateurId;

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<ProduitWithProduitModule> getProduitWithModule() {
        return produitWithModule;
    }

    public void setProduitWithModule(List<ProduitWithProduitModule> produitWithModule) {
        this.produitWithModule = produitWithModule;
    }

    public List<Integer> getListUtilisateurId() {
        return listUtilisateurId;
    }

    public void setListUtilisateurId(List<Integer> listUtilisateurId) {
        this.listUtilisateurId = listUtilisateurId;
    }
}
