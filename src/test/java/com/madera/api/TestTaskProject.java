package com.madera.api;

import com.madera.api.controllers.TaskProject;
import com.madera.api.models.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class TestTaskProject {

    @Autowired
    private TaskProject taskProject;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAllproject() {
        ResponseEntity<Object> responseEntity = taskProject.getAllProject(null);
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateAll() {
        // Init
        ProjetWithAllInfos projetWithAllInfos = new ProjetWithAllInfos();
        Projet projet = new Projet();
        List<ProduitWithProduitModule> listProduitWithModule = new ArrayList<>();
        List<Integer> listUtilisateurId = new ArrayList<>();
        Produit produit1 = new Produit();
        Produit produit2 = new Produit();
        List<ProduitModule> listProduitModule1 = new ArrayList<>();
        List<ProduitModule> listProduitModule2 = new ArrayList<>();

        // Création projetTest
        projet.setNomProjet("testProjet");
        projet.setRefProjet("20191412-testprojet-4");
        projet.setDateProjet(Date.valueOf(LocalDate.now()));
        projet.setPrixTotal(301254);
        projet.setClientId(4);
        projet.setDevisEtatId(1);
        // Test si le résultat est null
        assertNotNull(projet);

        // Création produitTest_1
        produit1.setProduitNom("Maison test");
        produit1.setGammesId(1);
        produit1.setPrixProduit(301254);
        produit1.setModele(false);
        // Test si le résultat est null
        assertNotNull(produit1);

        // Création produitTest_2
        produit2.setProduitNom("Bungalow");
        produit2.setGammesId(2);
        produit2.setPrixProduit(502980);
        produit2.setModele(false);
        // Test si le résultat est null
        assertNotNull(produit2);

        // Création listProduitModule pour produit1
        listProduitModule1.add(new ProduitModule(1, "Mur ext.", "Angle sortant",
                "{\"sections\": [{\"longueur\": 450}, {\"longueur\": 630}]}", 0.0));
        listProduitModule1.add(new ProduitModule(2, "Mur int.", "Angle entrant",
                "{\"sections\": [{\"longueur\": 520}, {\"longueur\": 400}]}", 0.0));
        listProduitModule1.add(new ProduitModule(4, "Toit plat", "", "{\"sections\": [{\"longueur\": 750}]}", 0.0));
        // Test si le résultat est null
        assertNotNull(listProduitModule1);

        // Création listProduitModule pour produit2
        listProduitModule2.add(new ProduitModule(5, "Mur ext. premium", "Angle sortant",
                "{\"sections\": [{\"longueur\": 500}, {\"longueur\": 500} ]}", 0.0));
        listProduitModule2.add(new ProduitModule(2, "Mur int.", "Angle entrant",
                "{\"sections\": [{\"longueur\": 520}, {\"longueur\": 400}]}", 0.0));
        listProduitModule2.add(new ProduitModule(4, "Toit", "", "{\"sections\": [{\"longueur\": 750}]}", 0.0));
        // Test si le résultat est null
        assertNotNull(listProduitModule2);

        // Création produitWithModule
        listProduitWithModule.add(new ProduitWithProduitModule(produit1, listProduitModule1));
        listProduitWithModule.add(new ProduitWithProduitModule(produit2, listProduitModule2));
        // Test si le résultat est null
        assertNotNull(listProduitWithModule);

        // Création listUtilisateurId
        listUtilisateurId.add(4);
        listUtilisateurId.add(1);
        // Test si le résultat est null
        assertNotNull(listUtilisateurId);

        // Création projetWithAllInfos
        projetWithAllInfos.setProjet(projet);
        projetWithAllInfos.setProduitWithModule(listProduitWithModule);
        projetWithAllInfos.setListUtilisateurId(listUtilisateurId);
        assertNotNull(projetWithAllInfos);

        // Insertion
        ResponseEntity<Object> responseEntity = taskProject.createProject(projetWithAllInfos);

        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteProject() {
        ResponseEntity<Object> responseEntity = taskProject.deleteProjectByRef("20191412-testprojet-4");
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
