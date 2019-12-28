package com.madera.api.models;

import java.util.List;

public class ProduitWithModule {
    Produit produit;

    List<ProduitModule> listModules;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<ProduitModule> getListModules() {
        return listModules;
    }

    public void setListModules(List<ProduitModule> listModules) {
        this.listModules = listModules;
    }
}
