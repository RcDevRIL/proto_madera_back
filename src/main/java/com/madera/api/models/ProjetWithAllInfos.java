package com.madera.api.models;

import java.util.List;

public class ProjetWithAllInfos {
    Projet projet;

    List<ProduitWithModule> produitWithModule;

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<ProduitWithModule> getProduitWithModule() {
        return produitWithModule;
    }

    public void setProduitWithModule(List<ProduitWithModule> produitWithModule) {
        this.produitWithModule = produitWithModule;
    }
}
