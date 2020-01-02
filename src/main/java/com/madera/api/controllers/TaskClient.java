package com.madera.api.controllers;

import com.madera.api.models.Client;
import com.madera.api.models.ClientAdresse;
import com.madera.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class TaskClient {

    @Autowired
    private final ClientRepository clientRepository;

    public TaskClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // TODO get Client by id

    @GetMapping(path = "/client", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getClient(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) {
        Map<String, Object> mapResponse = new HashMap<>();
        Client client = clientRepository.getClientByNomAndPrenom(nom, prenom);
        mapResponse.put("client", client);
        if (client != null) {
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/client", consumes = "application/json")
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        // TODO client_adresse et adresse ?
        Map<String, Object> mapResponse = new HashMap<>();
        Integer clientId = clientRepository.createClient(client);
        if (clientId != null) {
            mapResponse.put("clientId", clientId);
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/client", consumes = "application/json")
    public ResponseEntity<Object> updateClient(@RequestBody Client client) {
        boolean isUpdated = clientRepository.updateClient(client) == 1;
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/client", consumes = "application/json")
    public ResponseEntity<Object> deleteClient(@RequestBody Integer clientId) {
        boolean isDeleted = clientRepository.deleteClient(clientId) != 0;
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/clientadresse", consumes = "application/json")
    public ResponseEntity<Object> addClientAdresse(@RequestBody List<ClientAdresse> listClientAdresse) {
        Map<String, Object> mapResponse = new HashMap<>();
        boolean isInserted = clientRepository.addClientAdresse(listClientAdresse) != 0;
        if (isInserted) {
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
