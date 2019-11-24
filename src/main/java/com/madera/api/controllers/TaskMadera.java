package com.madera.api.controllers;

import com.madera.api.models.Composant;
import com.madera.api.models.Gamme;
import com.madera.api.repository.ReferentielRepository;
import org.jooq.Record;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.madera.api.models.User;
import com.madera.api.repository.UserRepository;

@RestController
@RequestMapping(path = "/madera")
public class TaskMadera {

    private static final Logger log = LoggerFactory.getLogger(TaskMadera.class);

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ReferentielRepository referentielRepository;

    public TaskMadera(UserRepository userRepository, ReferentielRepository referentielRepository) {
        this.userRepository = userRepository;
        this.referentielRepository = referentielRepository;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    // TODO Vérifier si utilisateur est connecté ou non
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/authentification", consumes = "application/json")
    public ResponseEntity<Object> authentification(@RequestBody User user) {
        Map<String, String> mapResponse = new HashMap<>();
        log.debug("Try connection for user {}", user.getLogin());
        if (!user.getLogin().isEmpty() && !user.getPassword().isEmpty()) {
            Result<Record> result = userRepository.checkUser(user);
            if (!result.isEmpty()) {
                String token = UUID.randomUUID().toString();
                userRepository.insertToken(user, token);
                log.debug("Connection successful");
                mapResponse.put("token", token);
                return new ResponseEntity<>(mapResponse, HttpStatus.OK);
            }
        }
        log.debug("Connection failure");
        mapResponse.put("token", "false");
        return new ResponseEntity<>("Connection failed", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path = "/referentiel", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getReferentiel() {
        Map<String, Object> mapResponse = new HashMap<>();
        List<Composant> listComposants= referentielRepository.getAllComposant();
        List<Gamme> listGammes = referentielRepository.getAllGammes();
        mapResponse.put("composant", listComposants);
        mapResponse.put("gammes", listGammes);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/createProject", consumes = "application/json")
    public ResponseEntity<Object> createProject() {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);}

    @PutMapping(path = "/updateProject", consumes = "application/json")
    public ResponseEntity<Object> updateProject() {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/projects", consumes = "application/json")
    public ResponseEntity<Object> getAllProject() {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/quote/{id}", consumes = "application/json")
    public ResponseEntity<Object> getQuote(@RequestParam Integer id) {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }
}
