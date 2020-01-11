package com.madera.api.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.madera.api.models.Adresse;
import com.madera.api.models.User;
import com.madera.api.models.UserAuth;
import com.madera.api.repository.ClientRepository;
import com.madera.api.repository.UserRepository;
// import com.madera.api.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
// import java.util.Map;
import java.util.UUID;

/**
 * Controlleur principal pour exposer nos différents services.
 *
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.4-RELEASE
 */
@RestController
@RequestMapping(path = "/api")
public class TaskMadera {

    private static final Logger log = LoggerFactory.getLogger(TaskMadera.class);

    @Autowired
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    /**
     * Constructeur TaskMadera
     * 
     * @param userRepository   userRepository
     * @param clientRepository clientRepository
     */
    public TaskMadera(UserRepository userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Endpoint servant à tester la joignabilité du serveur
     * 
     * @return Ok
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Object> index() {
        // TODO Afficher l'IP de la machine qui ping si possible
        log.info("Received ping from somewhere");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * EndPoint authentification
     * 
     * @param userAuth UserAuth
     * @return Ok ou unauthorized
     */
    @PostMapping(path = "/authentification", consumes = "application/json")
    public ResponseEntity<Object> authentification(@RequestBody UserAuth userAuth) {
        log.info("Try connection for user {}", userAuth.getLogin());
        if (!userAuth.getLogin().isEmpty() && !userAuth.getPassword().isEmpty()) {
            User user = userRepository.checkUser(userAuth);
            if (user != null) {
                // Map<String, String> mapResponse = new HashMap<>();
                // TODO Générer un token avec des librairies SSL ?
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

    /**
     * EndPoint deconnection
     * 
     * @param login login de la personne a deconnecter
     * @return Ok ou BadRequest
     */
    @PostMapping(path = "/deconnection", consumes = "application/json")
    public ResponseEntity<Object> deconnection(@RequestBody String login) {
        int isDelete = userRepository.deleteToken(login);
        if (isDelete != 1) {
            return new ResponseEntity<>("Error with deconnection", HttpStatus.BAD_REQUEST);
        } else {
            log.info("User logged out");
            return new ResponseEntity<>("User deconnected", HttpStatus.OK);
        }
    }

    /**
     * EndPoint pour ajouter des adresses
     * 
     * @param listAdresse listAdresse
     * @return Ok ou BadRequest
     */
    @PostMapping(path = "/adresse", consumes = "application/json")
    public ResponseEntity<Object> createAdresse(@RequestBody List<Adresse> listAdresse) {
        boolean isInserted = clientRepository.createAdresse(listAdresse) != 0;
        if (isInserted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/devis/{projet_id}", consumes = "application/json")
    public ResponseEntity<Object> generateDevisPdf(@PathVariable("projet_id") Integer projetId) {
        //TODO a mettre dans une classe a part et commenter
        //Création du document
        Document document = new Document();
        try {
            //Défini que document est un pdf
            PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
            document.open();
            BaseFont fontCalibriBaseFont = BaseFont.createFont("src/main/resources/fonts/OpenSans-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            Font fontTitle = new Font(fontCalibriBaseFont);
            Chunk chunk = new Chunk("DEVIS [nomProjet]", fontTitle);
            document.add(chunk);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
