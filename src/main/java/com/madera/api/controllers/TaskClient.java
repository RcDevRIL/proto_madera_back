package com.madera.api.controllers;

import com.madera.api.models.Client;
import com.madera.api.models.ClientAdresse;
import com.madera.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class TaskClient {

    @Autowired
    private ClientRepository clientRepository;

    public TaskClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping(path = "/client", consumes = "application/json")
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        //TODO client_adresse et adresse ?
        Map<String, Object> mapResponse = new HashMap<>();
        Integer clientId = clientRepository.createClient(client);
        if(clientId != null) {
            mapResponse.put("clientId", clientId);
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/clientadresse", consumes = "application/json")
    public ResponseEntity<Object> addClientAdresse(@RequestBody List<ClientAdresse> listClientAdresse) {
        Map<String, Object> mapResponse = new HashMap<>();
        clientRepository.addClientAdresse(listClientAdresse);
        //TODO gérer si adresse pas renseigné ?
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }
}
