package com.madera.api.controllers;

import com.madera.api.models.*;
import com.madera.api.models.Module;
import com.madera.api.repository.ClientRepository;
import com.madera.api.repository.ProjetRepository;
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
    private final ProjetRepository projetRepository;
    private final ClientRepository clientRepository;

    private final Helper helper = new Helper();

    public TaskMadera(UserRepository userRepository, ReferentielRepository referentielRepository, ProjetRepository projetRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.referentielRepository = referentielRepository;
        this.projetRepository = projetRepository;
        this.clientRepository = clientRepository;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Object> index() {
        // TODO Afficher l'IP de la machine qui ping si possible
        log.info("Received ping from somewhere");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/authentification", consumes = "application/json")
    public ResponseEntity<Object> authentification(@RequestBody UserAuth userAuth) {
        log.info("Try connection for user {}", userAuth.getLogin());
        if (!userAuth.getLogin().isEmpty() && !userAuth.getPassword().isEmpty()) {
            User user = userRepository.checkUser(userAuth);
            if (user != null) {
                Map<String, String> mapResponse = new HashMap<>();
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

    @PostMapping(path = "/deconnection", consumes = "application/json")
    public ResponseEntity<Object> deconnection(@RequestBody String login) {
        int isDelete = userRepository.deleteToken(login);
        if(isDelete != 1) {
            return new ResponseEntity<>("Error with deconnection", HttpStatus.BAD_REQUEST);
        } else {
            log.info("User logged out");
            return new ResponseEntity<>("User deconnected", HttpStatus.OK);
        }
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

        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @GetMapping(path= "/synchro/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getSynchro(@PathVariable("id") Integer utilisateurId) {
        Map<String, Object> mapResponse = new HashMap<>();

        List<Projet> listProjet = projetRepository.getAllProjectsByUserId(utilisateurId);
        List<ProjetModule> listProjetModule = projetRepository.getAllProjectModuleByUserId(utilisateurId);
        List<Client> listClient = clientRepository.getAllClient();
        List<ClientAdresse> listClientAdresse = clientRepository.getAllClientAdresse();
        List<Adresse> listAdresse = projetRepository.getAllAdresse();

        mapResponse.put("projet", listProjet);
        mapResponse.put("projetModule", listProjetModule);
        mapResponse.put("client", listClient);
        mapResponse.put("clientAdresse", listClientAdresse);
        mapResponse.put("adresse", listAdresse);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/createProject", consumes = "application/json")
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

    @PutMapping(path = "/updateProject", consumes = "application/json")
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

    @PostMapping(path = "/createClient", consumes = "application/json")
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        //TODO client_adresse et adresse ?
        Map<String, Object> mapResponse = new HashMap<>();
        Integer clientId = clientRepository.createClient(client);
        if(clientId != null) {
            mapResponse.put("clientId", clientId);
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/addClientAdresse", consumes = "application/json")
    public ResponseEntity<Object> addClientAdresse(@RequestBody List<ClientAdresse> listClientAdresse) {
        Map<String, Object> mapResponse = new HashMap<>();
        clientRepository.addClientAdresse(listClientAdresse);
        //TODO gérer si adresse pas renseigné ?
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/createAdresse", consumes = "application/json")
    public ResponseEntity<Object> createAdresse(@RequestBody List<Adresse> listAdresse) {
        clientRepository.createAdresse(listAdresse);
        //TODO gérer si adresse pas renseigné ?
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
