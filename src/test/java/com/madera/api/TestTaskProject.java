package com.madera.api;

import com.madera.api.controllers.TaskProject;
import com.madera.api.models.Projet;
import com.madera.api.models.ProduitModule;
import org.json.JSONException;
import org.json.JSONObject;
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
import static org.junit.Assert.*;

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
        // TODO Mettre l'id de l'utilisateur de test
        ResponseEntity<Object> responseEntity = taskProject.getAllProject(null);
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    //TODO refaire
    /*@Test
    public void testCreateProject() {
        Projet projet = new Projet();
        projet.setNomProjet("testProjet");
        projet.setRefProjet("20191412-testprojet-4");
        projet.setDateProjet(Date.valueOf(LocalDate.now()));
        projet.setPrix(301254);
        projet.setClientId(4);
        projet.setDevisEtatId(1);

        List<ProduitModule> listProjetModule = new ArrayList<>();
        listProjetModule.add(new ProduitModule(1));
        listProjetModule.add(new ProduitModule(2));
        listProjetModule.add(new ProduitModule(3));
        listProjetModule.add(new ProduitModule(4));
        ResponseEntity<Object> responseEntity = taskProject.createProject(projet, listProjetModule, 4);
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        try {
            projet.setProjetId(new JSONObject(responseEntity.getBody().toString()).getInt("projetId"));
            // Test si le projetId est différent de 0
            assertNotEquals(projet.getProjetId(), Integer.valueOf(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateProject() {
        Projet projet = new Projet();
        projet.setNomProjet("testProjetUpdate");
        projet.setRefProjet("20191412-testprojet-4");
        projet.setDateProjet(Date.valueOf(LocalDate.now()));
        projet.setPrix(301254);
        projet.setClientId(4);
        projet.setDevisEtatId(4);

        List<ProduitModule> listProjetModule = new ArrayList<>();
        listProjetModule.add(new ProduitModule(1));
        listProjetModule.add(new ProduitModule(2));
        listProjetModule.add(new ProduitModule(3));
        listProjetModule.add(new ProduitModule(4));
        listProjetModule.add(new ProduitModule(3));

        ResponseEntity<Object> responseEntity = taskProject.updateProject(projet, listProjetModule, 4);
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
        try {
            Boolean isDeleted = new JSONObject(responseEntity.getBody().toString()).getBoolean("isDeleted");
            // Test si le projet a bien été supprimé
            assertTrue(isDeleted);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        ResponseEntity<Object> responseEntity = taskProject.getQuote(0);
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }*/
}
