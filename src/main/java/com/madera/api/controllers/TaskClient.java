package com.madera.api.controllers;

import com.madera.api.models.Client;
import com.madera.api.models.ClientAdresse;
import com.madera.api.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(TaskClient.class);

    @Autowired
    private final ClientRepository clientRepository;

    public TaskClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Récupérer un client
     * 
     * @param nom    du client
     * @param prenom du client
     * @return Ok avec le client ou BadRequest
     */
    @GetMapping(path = "/client", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getClient(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) {
        log.info("GET /client called");
        Map<String, Object> mapResponse = new HashMap<>();
        Client client = clientRepository.getClientByNomAndPrenom(nom, prenom);
        mapResponse.put("client", client);
        if (client != null) {
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * EndPoint création d'un client
     * 
     * @param client json(depuis un appel serveur) to client
     * @return le clientId ajouté
     */
    @PostMapping(path = "/client", consumes = "application/json")
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        log.info("POST /client called");
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

    /**
     * Méthode de mise à jour du client
     * 
     * @param client json(depuis un appel serveur) to client
     * @return Ok ou BadRequest
     */
    @PutMapping(path = "/client", consumes = "application/json")
    public ResponseEntity<Object> updateClient(@RequestBody Client client) {
        log.info("PUT /client called");
        boolean isUpdated = clientRepository.updateClient(client) == 1;
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * EndPoint Suppression client
     * 
     * @param clientId integer
     * @return Ok ou BadRequest
     */
    @DeleteMapping(path = "/client", consumes = "application/json")
    public ResponseEntity<Object> deleteClient(@RequestBody Integer clientId) {
        log.info("DELETE /client called");
        boolean isDeleted = clientRepository.deleteClient(clientId) != 0;
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint pour ajouter une liste d'adresse à un client
     * 
     * @param listClientAdresse listClientAdresse
     * @return OK ou BadRequest
     */
    @PostMapping(path = "/clientadresse", consumes = "application/json")
    public ResponseEntity<Object> addClientAdresse(@RequestBody List<ClientAdresse> listClientAdresse) {
        log.info("POST /clientadresse called");
        Map<String, Object> mapResponse = new HashMap<>();
        boolean isInserted = clientRepository.addClientAdresse(listClientAdresse) != 0;
        if (isInserted) {
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
