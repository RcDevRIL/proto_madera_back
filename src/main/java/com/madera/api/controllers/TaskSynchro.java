package com.madera.api.controllers;

import com.madera.api.models.Module;
import com.madera.api.models.*;
import com.madera.api.repository.ClientRepository;
import com.madera.api.repository.ProjetRepository;
import com.madera.api.repository.ReferentielRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlleur synchro pour la synchronisation des données.
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-RELEASE
 */
@RestController
@RequestMapping("/api")
public class TaskSynchro {

    private static final Logger log = LoggerFactory.getLogger(TaskSynchro.class);

    @Autowired
    private final ReferentielRepository referentielRepository;
    private final ProjetRepository projetRepository;
    private final ClientRepository clientRepository;

    public TaskSynchro(ReferentielRepository referentielRepository, ProjetRepository projetRepository, ClientRepository clientRepository) {
        this.referentielRepository = referentielRepository;
        this.projetRepository = projetRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping(path = "/referentiel", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getReferentiel() {
        Map<String, Object> mapResponse = new HashMap<>();

        List<Composant> listComposants= referentielRepository.getAllComposant();
        List<Gamme> listGammes = referentielRepository.getAllGammes();
        List<Module> listModules = referentielRepository.getAllModules();
        List<ModuleComposant> listModuleComposants = referentielRepository.getAllModuleComposant();
        List<ComposantGroupe> listComposantGroupe = referentielRepository.getAllComposantGroupe();
        List<DevisEtat> listDevisEtat = referentielRepository.getAllDevisEtat();

        mapResponse.put("composant", listComposants);
        mapResponse.put("composantGroupe", listComposantGroupe);
        mapResponse.put("gammes", listGammes);
        mapResponse.put("module", listModules);
        mapResponse.put("moduleComposant", listModuleComposants);
        mapResponse.put("devisEtat", listDevisEtat);
        mapResponse.put("produitModele", null);

        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @GetMapping(path= "/synchro/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getSynchro(@PathVariable("id") Integer utilisateurId) {
        Map<String, Object> mapResponse = new HashMap<>();

        List<Projet> listProjet = projetRepository.getAllProjectsByUserId(utilisateurId);
        List<ProduitModule> listProjetModule = projetRepository.getAllProduitModuleByUserId(utilisateurId);
        //TODO projetproduits
        List<Client> listClient = clientRepository.getAllClient();
        List<ClientAdresse> listClientAdresse = clientRepository.getAllClientAdresse();
        List<Adresse> listAdresse = projetRepository.getAllAdresse();

        mapResponse.put("projet", listProjet);
        mapResponse.put("produitModule", listProjetModule);
        mapResponse.put("client", listClient);
        mapResponse.put("clientAdresse", listClientAdresse);
        mapResponse.put("adresse", listAdresse);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }
}
