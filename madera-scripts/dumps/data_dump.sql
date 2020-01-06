INSERT INTO madera.role(i_role_id, v_libelle_role) VALUES
(1, 'Développeur'),
(2, 'Commercial'),
(3, 'Test');

SELECT setval('madera.role_i_role_id_seq'::regclass, 2);

INSERT INTO madera.utilisateur(i_utilisateur_id, v_nom, v_prenom, v_mail, v_tel, i_role_id, v_login) VALUES
(1, 'LADOUCE', 'Fabien', 'ladouce.fabien@gmail.com', '0600000000', 2, 'ladouce.fabien'),
(2, 'HELIOT', 'David', 'boite.sphinx@gmail.com', '0600000000', 2, 'boite.sphinx'),
(3, 'CHEVALLIER', 'Romain', 'romain.chevallier@gmail.com', '0600000000', 2, 'romain.chevallier');


INSERT INTO madera.utilisateur(i_utilisateur_id, v_nom, v_prenom, v_mail, v_tel, i_role_id, v_login, v_password) VALUES
(4, 'test', 'user', 'user@test.com', '0000000000', 3, 'testuser', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');

SELECT setval('madera.utilisateur_i_utilisateur_id_seq'::regclass, 4);

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

SELECT setval('madera.composant_referentiel_i_composant_referentiel_id_seq'::regclass, 6);

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

SELECT setval('madera.module_referentiel_i_module_referentiel_id_seq'::regclass, 6);

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

SELECT setval('madera.adresse_i_adresse_id_seq'::regclass, 10);

INSERT INTO madera.client(i_client_id, v_nom, v_prenom, v_tel, v_mail) VALUES
(1, 'DUBOIS', 'Claude', '0600000000', 'dubois.claude@gmail.com'),
(2, 'YAOURT', 'Clémentine', '0600000000', 'yaourt.clementine@gmail.com'),
(3, 'CHENE', 'Jean', '0600000000', 'chene.jean@outlook.com'),
(4, 'COMMUNE', 'Dijon', '0600000000', 'commune.dijon@dijon.fr'),
(5, 'VENTDANE', 'Julie', '0600000000', 'ventdane.julie@gmail.com');

SELECT setval('madera.client_i_client_id_seq'::regclass, 5);

INSERT INTO madera.client_adresse(i_client_id, i_adresse_id, b_adresse_facturation) VALUES
(1, 1, true),
(2, 4, true),
(3, 5, false),
(3, 6, true),
(4, 8, true),
(5, 3, true);

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

SELECT setval('madera.composant_groupe_i_composant_groupe_id_seq'::regclass, 15);

INSERT INTO madera.devis_etat(i_devis_etat_id, v_devis_etat_libelle, i_pourcentage_somme) VALUES
(1, 'A la Signature', 3),
(2, 'Brouillon', 0),
(3, 'En attente', 0),
(4, 'Accepté', 3),
(5, 'En refusé', 0),
(6, 'En commande', 10),
(7, 'Transfert en facturation', 15),
(8, 'Projet terminé', 100),
(9, 'Obtention du permis de construire', 10),
(10, 'Ouverture du chantier', 15),
(11, 'Achèvement des fondations', 25),
(12, 'Achèvement des murs', 40),
(13, 'Mise hors d''eau / hors d''air', 75),
(14, 'Achèvement des travaux d''équipement (plomberie, menuiserie, chauffage)', 95),
(15, 'Remise des clés', 100);

SELECT setval('madera.devis_etat_i_devis_etat_id_seq'::regclass, 15);

INSERT INTO madera.gammes(i_gammes_id, v_libelle_gammes) VALUES
(1, 'Standard'),
(2, 'Premium');

SELECT setval('madera.gammes_i_gammes_id_seq'::regclass, 2);

INSERT INTO madera.composant(i_composant_id, i_composant_groupe_id, v_libelle, i_composant_referentiel_id, f_section, f_composant_prix) VALUES
(1, 10, 'Crépi minéral', 4 , 3, 29.99),
-- Equivalent au plâtre
-- 5 euros l'unité (plaque de plâtre)
(2, 13, 'Gypse-phaser', 4 , 12, 5),
-- Isolant
-- 7 euros le m2
(3, 3, 'EPS / XPS', 4 , 30, 7),
-- Panneau structurel
(4, 10, 'OSB', 4 , 10, 56.99),
-- Ossature
(5, 11, 'Chassis de bois', 4 , 120, 159),
-- Isolant
-- 12.55 le m2
(6, 3, 'Ouate minérale', 3 , 1.2, 12.55),
-- Pare vapeur
(7, 12, 'Folio isolé sur vapeur', 3 , 1, 87),
-- Panneau structurel
(8, 13, 'Carton de plâtre', 4 , 12.5, 65.23),
-- Finition intérieure
(9, 9, 'Ponçage et peinture', 4 , 2, 21),
-- Planchers
-- 23.50 l'unité
(10, 10, 'Parquette laminée', 5 , 10, 23.50),
-- Panneau structurel
(11, 10, 'OSB', 4 , 18, 60.33),
-- Ossature
(12, 11, 'Chassis de bois', 4 , 200, 204.66),
-- Tasseau ?
(13, null, 'Construction carton de plâtre', null , 1, 25),
-- 12 euros le m2 ?
(14, 15, 'Tuiles', 6 , 1, 12),
-- Pare vapeur ?
(15, 12, 'Folio', 3, 1, 45),
-- Poutre de maintien
(16, 11, 'Poutre', 1, 16, 30),
-- Isolant
(17, 3, 'Ouate', 3 , 5, 50),
(18, 3, 'Ouate + folio d''aluminium', 3 , 5, 75),
-- Phaser
(19, 13, 'Phaser', 4, 3, 35),
-- Ouate
(20, 3, 'Ouate minérale', 3 , 100, 80),
-- Finition extérieure
(21, 10, 'Tuile immitation bois', 1, 5, 45),
-- Isolant
(22, 3, 'Laine de verre', 3, 5, 55),
-- Finition intérieure
(23, 9, 'Papier peint blanc à motifs', 4, 2, 20);

SELECT setval('madera.composant_i_composant_id_seq'::regclass, 23);

INSERT INTO madera.module(i_module_id, i_gammes_id, i_module_referentiel_id, v_nom, v_nature_module, f_prix_module) VALUES
(1, 1, 1, 'Mur extérieur', 'Mur extérieur', null),
(2, 1, 2, 'Mur intérieur', 'Mur intérieur', null),
(3, 1, 4, 'Mur intermédiaire', 'Mur intermédiaire', null),
(4, 1, 6, 'Toit', 'Toit', null),
(5, 2, 1, 'Mur extérieur immitation bois', 'Mur extérieur', null);

SELECT setval('madera.module_i_module_id_seq'::regclass, 5);

INSERT INTO madera.module_composant(i_composant_id, i_module_id, i_ordre) VALUES
-- Mur extérieur
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 9),
(8, 1, 8),
(9, 1, 9 ),
-- Mur intérieur
(9, 2, 1),
(8, 2, 2),
(4, 2, 3),
(5, 2, 4),
(6, 2, 5),
-- Mur intermédiaire
(10, 3, 1),
(11, 3, 2),
(12, 3, 3),
(20, 3, 4),
(13, 3, 5),
(8, 3, 6),
(9, 3, 7),
-- Toit
(14, 4, 1),
(15, 4, 2),
(11, 4, 3),
(16, 4, 4),
(17, 4, 5),
(18, 4, 6),
(19, 4, 7),
(8, 4, 8),
(9, 4, 9),
--Mur extérieur immitation bois
(21, 5, 1),
(2, 5, 2),
(3, 5, 3),
(4, 5, 4),
(5, 5, 5),
(22, 5, 6),
(7, 5, 9),
(8, 5, 8),
(23, 5, 9);

INSERT INTO madera.projet(i_projet_id, i_client_id, v_nom_projet, v_ref_projet, d_date_projet, i_devis_etat_id, f_prix_tota, is_synchro) VALUES
(1, 1, 'Petit maison', 'ref20191227_1', '2019-12-27', 2, null, true),
(2, 2, 'Projet dépendance', 'ref_20191027_2', '2019-10-27', 3, null, true);

SELECT setval('madera.projet_i_projet_id_seq'::regclass, 2);

-- TODO corriger les données aucun modele ne doit être associé aux clients ! Il le sélectionne c'est tout
INSERT INTO madera.produit(i_produit_id, v_produit_nom, i_gammes_id, f_prix_produit, b_modele) VALUES
(1, 'Maison modulaire standard', 1, null, true),
(2, 'Dépendance standard', 1, null, false),
(3, 'Dépendance premium', 2, null, true);

SELECT setval('madera.produit_i_produit_id_seq'::regclass, 3);

-- TODO Refaire jeu d'essai n'attribuer aucun produitModele au client !
INSERT INTO madera.projet_produits(i_projet_id, i_produit_id) VALUES
(1, 2);

-- Continuer d'ajouter les modules !
INSERT INTO madera.produit_module(i_produit_module_id, i_produit_id, i_module_id, v_produit_module_nom, v_produit_module_angle, j_section_longueur) VALUES
(1, 1, 1, 'Mur standard 1', 'Angle Sortant', '{"sections": [{"longueur": 500}, {"longueur": 350}]}'),
(2, 1, 2, 'Cloison droite', '', '{"sections": [{"longueur": 300}]}'),
(3, 1, 3, 'Mur intermédiaire (plafond)', '', '{"sections": [{"longueur": 500}]}'),
(4, 1, 4, 'Toit (Tuiles)', '', '{"sections": [{"longueur": 1000}]}'),
(5, 2, 5, 'Mur ext. immitation bois', 'Angle Entrant', '{"sections": [{"longueur": 700}, {"longueur": 700}]}');

SELECT setval('madera.produit_module_i_produit_module_id_seq'::regclass, 5);

INSERT INTO madera.projet_utilisateurs(i_utilisateur_id, i_projet_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1);

INSERT INTO madera.fournisseur(i_fournisseur_id, v_raison_soc, i_adresse_id) VALUES
(1, 'Leroy Merlin', 3),
(2, 'Dijon Bois', 7),
(3, '74 Toiture', 5),
(4, 'Vendeur de caillou professionnel', 6);

SELECT setval('madera.fournisseur_i_fournisseur_id_seq'::regclass, 4);

INSERT INTO madera.composant_fournisseur(i_composant_id, i_fournisseur_id, v_ref_fournisseur) VALUES
(1, 4, 'crepi_mineral_VCP'),
(2, 1, 'gyphe_phaser_LM'),
(3, 1, 'isolant_xps_LM'),
(4, 1, 'panneau_osb_10_LM'),
(5, 2, 'chassis_bois_120_LM'),
(6, 1, 'isolant_ouate_120_LM'),
(7, 1, 'pare_vapeur_LM'),
(8, 1, 'carton_platre_LM'),
(9, 1, 'peinture_LM'),
(10, 1, 'parquets_laminee_LM'),
(11, 1, 'panneau_OSB_18_LM'),
(12, 2, 'chassis_bois_200_LM'),
(13, 1, 'constr_carton_platres_LM'),
(14, 3, 'tuile_74'),
(15, 1, 'folio_LM'),
(16, 2, 'poutre_LM'),
(17, 1, 'isolant_ouate_LM'),
(18, 1, 'ioslant_oaute_alu_LM'),
(19, 4, 'phaser_VCP'),
(20, 1, 'isolant_ouate_100_LM');