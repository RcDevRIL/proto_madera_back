package com.madera.api.controllers;

import com.madera.api.models.User;
import com.madera.api.repository.ComposantRepository;
import com.madera.api.repository.UserRepository;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/madera")
public class TaskMadera {

    private static final Logger log = LoggerFactory.getLogger(TaskMadera.class);

    @Autowired
    private final UserRepository userRepository;
    private final ComposantRepository composantRepository;

    public TaskMadera(UserRepository userRepository, ComposantRepository composantRepository) {
        this.userRepository = userRepository;
        this.composantRepository = composantRepository;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index() {
        return "Coucou toi";
    }

    @PostMapping(path = "/authentification", consumes = "application/json")
    @ResponseBody
    //TODO Optimiser la méthode
    public Map<String, String> authentification(@RequestBody User user) {
        Map<String, String> mapResponse = new HashMap<>();
        log.debug("Try connection for user {}", user.getLogin());
        if(!user.getLogin().isEmpty() && !user.getPassword().isEmpty()) {
            Result result = userRepository.checkUser(user);
            if(!result.isEmpty()) {
                String token = UUID.randomUUID().toString();
                userRepository.insertToken(user, token);
                log.debug("Connection successful");
                mapResponse.put("token", token);
                return mapResponse;
            }
        }
        log.debug("Connection failure");
        mapResponse.put("token", "false");
        return mapResponse;
    }

    @GetMapping(path = "/referentiel", produces = "application/json")
    @ResponseBody
    public Map<String, Result> getReferentiel() {
        Map<String, Result> mapResponse = new HashMap<>();
        // TODO Vérifier si utilisateur est connecté ou non
        var result = composantRepository.getAllComposant();
        mapResponse.put("composant", result);
        return mapResponse;
    }

    @PostMapping(path = "/createProject", consumes = "application/json")
    public String createProject() {
        return "Create project";
    }

    @PutMapping(path = "/updateProject", consumes = "application/json")
    public String updateProject() {
        return "Update project";
    }

    @GetMapping(path = "/projects", consumes = "application/json")
    public String getAllProject() {
        return "Get all project";
    }

    @GetMapping(path = "/quote/{id}", consumes = "application/json")
    public String getQuote(@RequestParam Integer id) {
        return "Get quote";
    }
}
