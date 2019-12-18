package com.madera.api.controllers;

import com.madera.api.models.Projet;
import com.madera.api.models.ProjetModule;
import com.madera.api.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlleur projet pour gérer les méthodes relatives aux projets.
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-RELEASE
 */
@RestController
@RequestMapping("api")
public class TaskProject {


    @Autowired
    private final ProjetRepository projetRepository;

    public TaskProject(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    @PostMapping(path = "/project", consumes = "application/json")
    public ResponseEntity<Object> createProject(@RequestBody Projet projet, @RequestBody List<ProjetModule> listProjetModule, @RequestBody Integer utilisateurId) {
        Map<String, Object> mapResponse = new HashMap<>();
        Integer projetId = projetRepository.createProjet(projet, listProjetModule, utilisateurId);
        if(projetId != null) {
            mapResponse.put("projetId", projetId);
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/project", consumes = "application/json")
    public ResponseEntity<Object> updateProject() {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/projects/{id}", produces = "application/json")
    public ResponseEntity<Object> getAllProject(@PathVariable("id") Integer id) {
        Map<String, Object> mapResponse = new HashMap<>();
        List<Projet> listProjets = projetRepository.getAllProjectsByUserId(id);
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
