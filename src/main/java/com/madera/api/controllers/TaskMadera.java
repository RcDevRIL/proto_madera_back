package com.madera.api.controllers;

import com.madera.api.models.Adresse;
import com.madera.api.models.User;
import com.madera.api.models.UserAuth;
import com.madera.api.repository.ClientRepository;
import com.madera.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// import com.madera.api.utils.Helper;
// import java.util.HashMap;
// import java.util.Map;

/**
 * Controlleur principal pour exposer nos différents services.
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 1.0-RELEASE
 */
@RestController
@RequestMapping(path = "/api")
public class TaskMadera {

    private static final Logger log = LoggerFactory.getLogger(TaskMadera.class);

    @Autowired
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    /**
     * Constructeur TaskMadera
     * 
     * @param userRepository   userRepository
     * @param clientRepository clientRepository
     */
    public TaskMadera(UserRepository userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Endpoint servant à tester la joignabilité du serveur
     * 
     * @return Ok
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Object> index() {
        log.info("Received ping from somewhere");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * EndPoint authentification
     * 
     * @param userAuth UserAuth
     * @return Ok ou unauthorized
     */
    @PostMapping(path = "/authentification", consumes = "application/json")
    public ResponseEntity<Object> authentification(@RequestBody UserAuth userAuth) {
        log.info("Try connection for user {}", userAuth.getLogin());
        if (!userAuth.getLogin().isEmpty() && !userAuth.getPassword().isEmpty()) {
            User user = userRepository.checkUser(userAuth);
            if (user != null) {
                String token = UUID.randomUUID().toString();
                userRepository.insertToken(userAuth, token);
                user.setToken(token);
                log.info("Connection successful");
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        log.info("Connection failure");
        return new ResponseEntity<>("Connection failed", HttpStatus.UNAUTHORIZED);
    }

    /**
     * EndPoint deconnection
     * 
     * @param login login de la personne a deconnecter
     * @return Ok ou BadRequest
     */
    @PostMapping(path = "/deconnection", consumes = "application/json")
    public ResponseEntity<Object> deconnection(@RequestBody String login) {
        int isDelete = userRepository.deleteToken(login);
        if (isDelete != 1) {
            return new ResponseEntity<>("Error with deconnection", HttpStatus.BAD_REQUEST);
        } else {
            log.info("User logged out");
            return new ResponseEntity<>("User deconnected", HttpStatus.OK);
        }
    }

    /**
     * EndPoint pour ajouter des adresses
     * 
     * @param listAdresse listAdresse
     * @return Ok ou BadRequest
     */
    @PostMapping(path = "/adresse", consumes = "application/json")
    public ResponseEntity<Object> createAdresse(@RequestBody List<Adresse> listAdresse) {
        boolean isInserted = clientRepository.createAdresse(listAdresse) != 0;
        if (isInserted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
