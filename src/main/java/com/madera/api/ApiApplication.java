package com.madera.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée du backend Madera.
 * 
 * <a href="https://github.com/RcDevRIL/proto_madera_back">Lien du dépôt
 * GitHub</a>
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.3-RELEASE
 */
@SpringBootApplication
public class ApiApplication {

	private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);

	public static void main(String[] args) {
		log.info("Bienvenue sur le serveur permettant le bon fonctionnement de l'application métier Madera !!");
		SpringApplication.run(ApiApplication.class, args);
	}

}
