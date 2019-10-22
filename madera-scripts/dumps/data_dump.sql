INSERT INTO madera.role(i_role_id, v_libelle_role) VALUES
-- Temporaire ?
(1, 'Développeur'),
(2, 'Commercial');

INSERT INTO madera.utilisateur(i_utilisateur_id, v_nom, v_prenom, v_mail, v_tel, v_password, i_role_id) VALUES
-- Mdp : 123456
(1, 'LADOUCE', 'Fabien', 'ladouce.fabien@gmail.com', '0600000000', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 2),
-- Mdp : abcdef
(2, 'HELIOT', 'David', 'boite.sphinx@gmail.com', '0600000000', 'bef57ec7f53a6d40beb640a780a639c83bc29ac8a9816f1fc6c5c6dcd93c4721', 2),
-- Mdp : abc123
(3, 'CHEVALLIER', 'Romain', 'romain.chevallier@gmail.com', '0600000000', '6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', 2);

INSERT INTO madera.composant_referentiel(i_composant_referentiel_id, v_caracteristique, v_unite_usage)VALUES
-- Montants en bois pour la structure, nommés lisses ou contrefort
(1, 'Section en cm', 'Longueur en cm'),
-- Elements de montages, sabots métalliques, boulons, gougeons,
(2, 'Section en cm', 'Pièce'),
-- Panneaux d'isolation et pare-pluie
(3, 'Epaisseur en cm', 'Surface en M2'),
-- Panneaux intermédiaires et de couverture
(4, 'Section en mm', 'Surface en M2'),
-- Planchers
(5, 'Section en mm', 'Surface en M2'),
-- Couverture (tuiles ou ardoises)
(6, 'Longueur et largeur en mm', 'Surface en M2');

INSERT INTO madera.module_referentiel(i_module_referentiel_id, v_caracteristique, v_unite_usage) VALUES
-- Murs extérieurs
(1, 'Hauteur-Longueur', 'M Linéaire'),
-- Cloisons intérieures
(2, 'Hauteur-Longueur', 'M Linéaire'),
-- Plancher sur dalle
(3, 'Hauteur-Longueur', 'M2'),
-- Plancher porteur
(4, 'Hauteur-Longueur', 'M2'),
-- Ferme de charpente
(5, 'Longueur', 'Unité'),
-- Couverture
(6, 'Hauteur-Longueur', 'M2');

INSERT INTO madera.adresse(i_adresse_id, v_ville, v_code_postale, v_rue, v_complement, v_numero) VALUES
(1, 'Dijon', '21000', 'Rue Sully', '', '64E'),
(2, 'Saint-Apollinaire', '21540', 'rue de la Glacière ', '', '3'),
(3, 'Longvic', '21600', 'rue de Romelet', '', '4'),
(4, 'Talant', '21240', 'rue Paule Langevin', '', '10'),
(5, 'Annecy', '74000', 'boulevard de la Corniche', '8', '64E'),
(6, 'Meyzieu', '69330', 'avenue Henri Schneider', '', '21550'),
(7, 'Dijon', '21000', 'rue Alain Bombard', '', '12'),
(8, 'Lille', '59000', 'rue Frederic Combemale', '', '3'),
-- L'adresse de la véritable entreprise Madera
(9, 'La Roche sur Yon', '85000', 'Rue Enzo Ferrari', '', ''),
(10, 'Dax', '40100', 'rue d''Aspremont', '', '30');

-- TODO Mettre une colonne raison_soc ? Si c'est des entreprises
INSERT INTO madera.client(i_client_id, v_nom, v_prenom, v_tel, v_mail) VALUES
(1, 'DUBOIS', 'Claude', '0600000000', 'dubois.claude@gmail.com'),
(2, 'YAOURT', 'Clémentine', '0600000000', 'yaourt.clementine@gmail.com'),
(3, 'CHENE', 'Jean', '0600000000', 'chene.jean@outlook.com'),
(4, 'COMMUNE', 'Dijon', '0600000000', 'commune.dijon@dijon.fr'),
(5, 'VENTDANE', 'Julie', '0600000000', 'ventdane.julie@gmail.com');

INSERT INTO madera.client_adresse(i_client_id, i_adresse_id, b_adresse_facturation) VALUES
(1, 1, true),
(2, 4, true),
(3, 5, false),
(3, 6, true),
(4, 8, true),
(5, 3, true);

-- A voir quoi mettre dedans au fur-et à mesure (sans doute sujet à modification)
INSERT INTO madera.composant_groupe(i_composant_groupe_id, v_libelle_groupe) VALUES
(1, 'Lisses'),
(2, 'Contrefort'),
(3, 'Panneau d''isolation'),
(4, 'Pare-pluie'),
(5, 'Couverture'),
(6, 'Sabots métalliques'),
(7, 'Boulons'),
(8, 'Gougeons'),
(9, 'Finition intérieure'),
(10, 'Finition extérieure'),
(11, 'Ossature'),
(12, 'Pare-vapeur'),
(13, 'Panneau structurel'),
(14, 'Plancher'),
(15, 'Toit');

INSERT INTO madera.devis_etat(i_devis_etat_id, v_devis_etat_libelle, i_pourcentage_somme) VALUES
(1, 'A la Signature', 3),
(2, 'Obtention du permis de construire', 10),
(3, 'Ouverture du chantier', 15),
(4, 'Achèvement des fondations', 25),
(5, 'Achèvement des murs', 40),
(6, 'Mise hors d''eau / hors d''air', 75),
(7, 'Achèvement des travaux d''équipement (plomberie, menuiserie, chauffage)', 95),
(8, 'Remise des clés', 100);

INSERT INTO madera.gammes(i_gammes_id, v_libelle_gammes) VALUES
(1, 'Standard'),
(2, 'Premium');

INSERT INTO madera.composant(i_composant_id, i_composant_groupe_id, v_libelle, i_composant_referentiel_id, f_section) VALUES
(1, 10, 'Crépi minéral', 4 , 3),
-- Equivalent au plâtre
(2, 13, 'Gypse-phaser', 4 , 12),
-- Isolant
(3, 3, 'EPS / XPS', 4 , 30),
-- Panneau structurel
(4, 10, 'OSB', 4 , 10),
-- Ossature
(5, 11, 'Chassis de bois', 4 , 120),
-- Isolant
(6, 3, 'Ouate minérale', 3 , 1.2),
-- Pare vapeur
(7, 12, 'Folio isolé sur vapeur', 3 , 1),
-- Panneau structurel
(8, 13, 'Carton de plâtre', 4 , 12.5),
-- Finition intérieure
(9, 9, 'Ponçage et peinture', 4 , 2),
-- Planchers
(10, 10, 'Parquette laminée', 5 , 10),
-- Panneau structurel
(11, 10, 'OSB', 4 , 18),
-- Ossature
(12, 11, 'Chassis de bois', 4 , 200),
-- Tasseau ?
(13, null, 'Construction carton de plâtre', null , null),
(14, 15, 'Tuiles', 6 , null),
-- Pare vapeur ?
(15, 12, 'Folio', 3, 1),
-- Poutre de maintien
(16, 11, 'Poutre', 1, 16),
-- Isolant
(17, 3, 'Ouate', 3 , 5),
(18, 3, 'Ouate + folio d''aluminium', 3 , 5),
-- Phaser
(19, 13, 'Phaser', 4, 3);
