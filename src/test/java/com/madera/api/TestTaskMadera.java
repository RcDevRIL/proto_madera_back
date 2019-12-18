package com.madera.api;

import com.madera.api.controllers.TaskMadera;
import com.madera.api.models.Adresse;
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

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class TestTaskMadera {

	private static final Logger log = LoggerFactory.getLogger(TestTaskMadera.class);

	@Autowired
	private TaskMadera taskMadera;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testIndex() {
		ResponseEntity<Object> responseEntity = taskMadera.index();
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testAuthentification() {
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
	public void testDeconnection() {
		//Test avec infos correctes
		ResponseEntity<Object> responseEntity = taskMadera.deconnection("testuser");
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		//Test avec infos incorrectes *******************
		responseEntity = taskMadera.deconnection("testusers");
		// Test si le résultat est null
		assertNotNull(responseEntity);
		// Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	public void testCreateAdresse() {
		List<Adresse> listAdresse = new ArrayList<>();
		listAdresse.add(new Adresse("Lyon", "69000", "rue du charpentier", "", "13"));
		listAdresse.add(new Adresse("Lens", "62300", "rue de l'éclair", "", "21"));
		listAdresse.add(new Adresse("Dijon", "21000", "boulevard du bois", "", "5"));
		ResponseEntity<Object> responseEntity = taskMadera.createAdresse(listAdresse);
		//Test si lé résultat est null
		assertNotNull(responseEntity);
		//Test si la méthode renvoi un code 200
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
