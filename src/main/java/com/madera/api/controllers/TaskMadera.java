package com.madera.api.controllers;

import com.madera.api.ApiApplication;
import com.madera.api.models.User;
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

    public TaskMadera(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
