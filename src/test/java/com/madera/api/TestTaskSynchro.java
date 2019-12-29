package com.madera.api;

import com.madera.api.controllers.TaskSynchro;
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
public class TestTaskSynchro {

    @Autowired
    private TaskSynchro taskSynchro;

    @Test
    public void contextLoads() {
    }

    @Test
    public void responseReferentiel() {
        ResponseEntity<Object> responseEntity = taskSynchro.getReferentiel();
        // Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void responseSynchroData() {
        ResponseEntity<Object> responseEntity = taskSynchro.getSynchro(4);
        //Test si le résultat est null
        assertNotNull(responseEntity);
        // Test si la méthode renvoi un code 200
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
