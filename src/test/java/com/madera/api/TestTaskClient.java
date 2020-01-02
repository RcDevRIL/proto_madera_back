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

    //TODO reprendre
    /*@Test
    public void testCUpdateClient() {
        // Test avec des infos correctes
        //Recupére le client
        ResponseEntity<Object> responseEntity = taskClient.getClient("Toto", "Yaourt");
        Client client = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JSONObject clientJson = new JSONObject(responseEntity.getBody().toString());
            client = mapper.readValue(clientJson.get("client"), Client.class);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(client.toString());
        client.setMail("toto-yaourt@gmail.com");

        responseEntity = taskClient.updateClient(client);
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Test avec des infos incorrectes
        client.setId(-1);
        responseEntity = taskClient.updateClient(client);
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 400
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testDAddClientAdresse() {
        List<ClientAdresse> listClientAdresse = new ArrayList<>();
        //Id de l'utilisateur de test : 4
        listClientAdresse.add(new ClientAdresse(4, 1, false));
        listClientAdresse.add(new ClientAdresse(4, 7, true));
        ResponseEntity<Object> responseEntity = taskClient.addClientAdresse(listClientAdresse);
        // Test si le résultat est null
        assertNotNull(responseEntity);
        //Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testEDeleteClient() {
        // Test avec des infos correctes
        ResponseEntity<Object> responseEntity = taskClient.getClient("Toto", "Yaourt");
        Client client = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String clientJson = new JSONObject(responseEntity.getBody().toString()).get("client").toString();
            client = mapper.readValue(clientJson, Client.class);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        responseEntity = taskClient.deleteClient(client.getId());
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }*/
}
