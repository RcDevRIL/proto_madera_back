package com.madera.api;

import com.madera.api.controllers.TaskClient;
import com.madera.api.models.Client;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTaskClient {

    @Autowired
    private TaskClient taskClient;

    @Test
    public void testACreateClient() {
        Client client = new Client();
        client.setNom("Toto");
        client.setPrenom("Yaourt");
        client.setMail("toto.yaourt@gmail.com");
        client.setNumTel("0600000000");
        ResponseEntity<Object> responseEntity = taskClient.createClient(client);
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testBGetClient() {
        // Test avec des infos correctes
        ResponseEntity<Object> responseEntity = taskClient.getClient("Toto", "Yaourt");
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Test avec des infos incorrectes
        responseEntity = taskClient.getClient("Titi", "Yaourt");
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 400
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @SuppressWarnings(value = "unchecked")
    @Test
    public void testEDeleteClient() {
        // Test avec des infos correctes
        ResponseEntity<Object> responseEntity = taskClient.getClient("Toto", "Yaourt");
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Map<String, Client> body = (Map<String, Client>) responseEntity.getBody();
        assert body != null;
        Client client = body.get("client");
        responseEntity = taskClient.deleteClient(client.getId());
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
