package com.madera.api.controllers;

import com.madera.api.models.Projet;
import com.madera.api.models.ProjetWithAllInfos;
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

    /**
     * Structure attendue : projet : {produit: {infosProduits, listModules: infosModules}}
     * @param projetWithAllInfos projetWithAllInfos
     * @return Ok or BadRequest
     */
    @PostMapping(path = "/project", consumes = "application/json")
    public ResponseEntity<Object> createProject(
            @RequestBody ProjetWithAllInfos projetWithAllInfos)
    {
        Map<String, Object> mapResponse = new HashMap<>();
        List<Integer> listProduitId = projetRepository.createAll(projetWithAllInfos);
        if(listProduitId != null) {
            mapResponse.put("listProduitId", listProduitId);
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Structure attendue : projet : infosProjet.., produit :[{produit: {infosProduits, listModules: [infosModules, ...]}, {produit:...}]}
     * @param projetWithAllInfos projetWithAllInfos
     * @return Ok or badRequest
     */
    @PutMapping(path = "/project", consumes = "application/json")
    public ResponseEntity<Object> updateProject(@RequestBody ProjetWithAllInfos projetWithAllInfos)
    {
        Map<String, Object> mapResponse = new HashMap<>();
        boolean isUpdated = projetRepository.updateAll(projetWithAllInfos) != 0;
        if(isUpdated) {
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/projects/{id}", produces = "application/json")
    public ResponseEntity<Object> getAllProject(@PathVariable("id") Integer id) {
        Map<String, Object> mapResponse = new HashMap<>();
        List<Projet> listProjets = projetRepository.getAllProjectsByUserId(id);
        mapResponse.put("listProjets", listProjets);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    /**
     * Supprime le projet suivant la refProjet passé en param
     * @param refProjet reference du projet
     * @return Ok or BadRequest
     */
    @DeleteMapping(path = "/project/{refProjet}", consumes = "application/json")
    public ResponseEntity<Object> deleteProjectByRef(@PathVariable("refProjet") String refProjet) {
        boolean isDeleted = projetRepository.deleteAll(refProjet) != 0;
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retourne un devis ?
     * @param id id du projet
     * @return un devis ?
     */
    @GetMapping(path = "/quote/{id}", consumes = "application/json")
    public ResponseEntity<Object> getQuote(@PathVariable ("id") Integer id) {
        Map<String, Object> mapResponse = new HashMap<>();
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }
}
