package com.madera.api.controllers;

import com.madera.api.models.*;
import com.madera.api.repository.ClientRepository;
import com.madera.api.repository.ProjetRepository;
import com.madera.api.utils.DevisGenerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private final ClientRepository clientRepository;

    public TaskProject(ProjetRepository projetRepository, ClientRepository clientRepository) {
        this.projetRepository = projetRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Structure attendue : projet : {produit: {infosProduits, listModules: infosModules}}
     * @param projetWithAllInfos projetWithAllInfos
     * @return Ok or BadRequest
     */
    @GetMapping(path = "/project", consumes = "application/json")
    public ResponseEntity<Object> createProject(
            @RequestBody ProjetWithAllInfos projetWithAllInfos)
    {
        Map<String, Object> mapResponse = new HashMap<>();
        Integer projetId = projetRepository.createAll(projetWithAllInfos);
        if(projetId != null) {
            Projet projet = projetRepository.getProjetByProjetId(projetId);
            List<ProduitModule> listProjetModule = projetRepository.getAllProduitModuleByProjetId(projetId);
            List<ProjetProduits> listProjetProduits = projetRepository.getAllProjetProduitByProjetId(projetId);
            List<Produit> listProduits = projetRepository.getAllProduitByProjetId(projetId);
            mapResponse.put("projet", projet);
            mapResponse.put("projetProduits", listProjetProduits);
            mapResponse.put("produit", listProduits);
            mapResponse.put("produitModule", listProjetModule);
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
        try {
            projetRepository.updateAll(projetWithAllInfos);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>( HttpStatus.OK);
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


    @PostMapping(path = "/devis/{projet_id}/{utilisateur_id}", consumes = "application/json", produces = "application/pdf")
    public ResponseEntity<Object> generateDevisPdf(
            @PathVariable("projet_id") Integer projetId,
            @PathVariable("utilisateur_id") Integer utilisateurId)
    {
        //TODO Faire un objet pour ca ?
        Projet projet = projetRepository.getProjetByProjetId(projetId);
        DevisEtat devisEtat = projetRepository.getDevisEtatOfProject(projet.devisEtatId);
        Utilisateur utilisateur = projetRepository.getUtilisateurById(utilisateurId);
        Client client = clientRepository.getClientByProjetId(projetId);
        Adresse adresseFacturation = clientRepository.getAdresseByClientId(client.id);
        List<Produit> listProduit = projetRepository.getAllProduitByProjetId(projetId);
        List<ProduitModule> listProduitWithModle = projetRepository.getProduitModuleByProjetId(projetId);
        List<DevisEtat> listDevisEcheance = projetRepository.getDevisEtatEcheance();
        List<ModuleComposant> listModuleComposant = projetRepository.getModuleComposantByProjetId(projetId);
        List<Integer> listComposantId = new ArrayList<>();
        for(ModuleComposant moduleComposant : listModuleComposant) {
            listComposantId.add(moduleComposant.getComposantId());
        }
        List<Composant> listComposants = projetRepository.getComposantByModuleId(listComposantId);
        DevisGenerated devisGenerated = new DevisGenerated();
        byte[] pdfByte = devisGenerated.generate(projet, devisEtat, utilisateur, client, adresseFacturation, listProduit, listProduitWithModle, listDevisEcheance, listModuleComposant, listComposants);
        return ResponseEntity
                .ok()
                .header("Content-Disposition: attachment")
                .body(pdfByte);
    }
}
