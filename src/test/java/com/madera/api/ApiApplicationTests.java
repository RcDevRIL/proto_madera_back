package com.madera.api;

import com.madera.api.controllers.TaskMadera;
import com.madera.api.models.Projet;
import com.madera.api.models.ProjetModule;
import com.madera.api.models.UserAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Classe permettant d'exécuter des tests unitaires sur l'application
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.2-PRE-RELEASE
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class ApiApplicationTests {
	// TODO Tester plus que simplement un code 200, s'il y a des données de tests
	// c'est pas que pour les afficher ;)

	private static final Logger log = LoggerFactory.getLogger(ApiApplicationTests.class);

	@Autowired
	private TaskMadera taskMadera;

	@Test
	public void contextLoads() {
	}

	@Test
	public void responseAuthentification() {
		// Test avec infos correctes *******************

		UserAuth user = new UserAuth("testuser", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
		log.info("Connecting with {}", user.toString());
		ResponseEntity<Object> responseEntity = taskMadera.authentification(user);
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Test avec infos incorrectes *******************

		user = new UserAuth("test.user", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
		log.info("Connecting with {}", user.toString());
		responseEntity = taskMadera.authentification(user);
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
		user = new UserAuth("testuser", "8D969EEf6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
		log.info("Connecting with {}", user.toString());
		responseEntity = taskMadera.authentification(user);
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
	}

	@Test
	public void responseReferentiel() {
		ResponseEntity<Object> responseEntity = taskMadera.getReferentiel();
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void responseCreateProject() {
		Projet projet = new Projet();
		projet.setNomProjet("testProjet");
		projet.setRefProjet("20191412-testprojet-4");
		projet.setDateProjet(Date.valueOf(LocalDate.now()));
		projet.setPrix(301254);
		projet.setClientId(4);
		projet.setDevisEtatId(1);

		List<ProjetModule> listProjetModule = new ArrayList<>();
		listProjetModule.add(new ProjetModule(1));
		listProjetModule.add(new ProjetModule(2));
		listProjetModule.add(new ProjetModule(3));
		listProjetModule.add(new ProjetModule(4));
		ResponseEntity<Object> responseEntity = taskMadera.createProject(projet, listProjetModule, 4);
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void responseAllproject() {
		// TODO Mettre l'id de l'utilisateur de test
		ResponseEntity<Object> responseEntity = taskMadera.getAllProject(null);
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void responseUpdate() {
		ResponseEntity<Object> responseEntity = taskMadera.updateProject();
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void response() {
		ResponseEntity<Object> responseEntity = taskMadera.getQuote(0);
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}
