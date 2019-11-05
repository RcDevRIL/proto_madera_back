package com.madera.api.controllers;

import com.madera.api.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/madera")
public class TaskMadera {

    //TODO Mettres des logs à chaque entrer dans les méthodes ?
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index() {
        return "Coucou toi";
    }

    @PostMapping(path = "/authentification", consumes = "application/json")
    public Boolean authentification(@RequestBody User user) {
        //TODO Mettre dans les logs si la connection a échouée ?
        //TODO Faire des TEST ?
        //TODO Je modifirais le truc lorsque j'implémenterais le chiffrage et la connection bdd
        if(!user.getLogin().isEmpty() && !user.getPassword().isEmpty()) {
            if(user.getLogin().equals("login") && user.getPassword().equals("password")) {
                return true;
            }
        }
        return false;
    }
}
