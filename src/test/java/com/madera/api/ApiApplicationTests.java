package com.madera.api;

import com.madera.api.controllers.TaskMadera;
import com.madera.api.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class ApiApplicationTests {

	@Autowired
	private TaskMadera taskMadera;

	@Test
	public void contextLoads() {
	}

	@Test
	public void responseAuthentification() {
		User user = new User("ladouce.fabien", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
		ResponseEntity<Object> responseEntity = taskMadera.authentification(user);
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void responseReferentiel() {
		ResponseEntity<Object> responseEntity = taskMadera.getReferentiel();
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void responseCreatProject() {
		ResponseEntity<Object> responseEntity = taskMadera.createProject();
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void responseAllproject() {
		//TODO Mettre l'id de l'utilisateur de test
		ResponseEntity<Object> responseEntity = taskMadera.getAllProject(null);
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void responseUpdate() {
		ResponseEntity<Object> responseEntity = taskMadera.updateProject();
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void response() {
		ResponseEntity<Object> responseEntity = taskMadera.getQuote(0);
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

}
