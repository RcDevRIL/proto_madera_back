package com.madera.api.controllers;

import com.madera.api.models.*;
import com.madera.api.models.Module;
import com.madera.api.repository.ReferentielRepository;
import com.madera.api.repository.UserRepository;
import com.madera.api.utils.Helper;
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

/**
 * Controlleur principal pour exposer nos différents services.
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-RELEASE
 */
@RestController
@RequestMapping(path = "/madera")
public class TaskMadera {

    private static final Logger log = LoggerFactory.getLogger(TaskMadera.class);

    @Autowired
    private final UserRepository userRepository;
    private final ReferentielRepository referentielRepository;

    private final Helper helper = new Helper();

    public TaskMadera(UserRepository userRepository, ReferentielRepository referentielRepository) {
        this.userRepository = userRepository;
        this.referentielRepository = referentielRepository;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Object> index() {
        // TODO Afficher l'IP de la machine qui ping si possible
        log.info("Received ping from somewhere");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/authentification", consumes = "application/json")
    public ResponseEntity<Object> authentification(@RequestBody User user) {
        Map<String, String> mapResponse = new HashMap<>();
        log.info("Try connection for user {}", user.getLogin());
        if (!user.getLogin().isEmpty() && !user.getPassword().isEmpty()) {
            Result<Record> result = userRepository.checkUser(user);
            if (!result.isEmpty()) {
                String token = UUID.randomUUID().toString();
                userRepository.insertToken(user, token);
                log.info("Connection successful");
                mapResponse.put("token", token);
                return new ResponseEntity<>(mapResponse, HttpStatus.OK);
            }
        }
        log.info("Connection failure");
        mapResponse.put("token", "false");
        return new ResponseEntity<>("Connection failed", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path = "/referentiel", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getReferentiel() {
        Map<String, Object> mapResponse = new HashMap<>();

        //TODO A revoir (faut que je regarde un truc au travail)
        List<Composant> listComposants= referentielRepository.getAllComposant();
        List<Gamme> listGammes = referentielRepository.getAllGammes();
        List<Module> listModules = referentielRepository.getAllModules();
        List<ModuleComposant> listModuleComposants = referentielRepository.getAllModuleComposant();

        mapResponse.put("composant", listComposants);
        mapResponse.put("gammes", listGammes);
        mapResponse.put("module", listModules);
        mapResponse.put("moduleComposant", listModuleComposants);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @GetMapping(path= "/synchro/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getSynchro() {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/createProject", consumes = "application/json")
    public ResponseEntity<Object> createProject() {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/updateProject", consumes = "application/json")
    public ResponseEntity<Object> updateProject() {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/projects/{id}", produces = "application/json")
    public ResponseEntity<Object> getAllProject(@PathVariable("id") Integer id) {
        Map<String, Object> mapResponse = new HashMap<>();
        List<Projet> listProjets = referentielRepository.getAllProjects(id);
        System.out.println(listProjets);
        mapResponse.put("listProjets", listProjets);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/quote/{id}", consumes = "application/json")
    public ResponseEntity<Object> getQuote(@RequestParam Integer id) {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }
}
