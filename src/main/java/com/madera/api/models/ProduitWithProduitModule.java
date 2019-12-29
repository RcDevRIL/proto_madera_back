package com.madera.api.models;

import java.util.List;

public class ProduitWithProduitModule {
    Produit produit;

    List<ProduitModule> listModules;

    public ProduitWithProduitModule() {
    }

    public ProduitWithProduitModule(Produit produit, List<ProduitModule> listModules) {
        this.produit = produit;
        this.listModules = listModules;
    }

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
