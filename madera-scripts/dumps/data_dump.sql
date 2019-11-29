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
(4, 'TEST', 'USER', 'user@test.com', '0000000000', 3, 'TEST.USER', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');

SELECT setval('madera.utilisateur_i_utilisateur_id_seq'::regclass, 3);

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
(2, 'Obtention du permis de construire', 10),
(3, 'Ouverture du chantier', 15),
(4, 'Achèvement des fondations', 25),
(5, 'Achèvement des murs', 40),
(6, 'Mise hors d''eau / hors d''air', 75),
(7, 'Achèvement des travaux d''équipement (plomberie, menuiserie, chauffage)', 95),
(8, 'Remise des clés', 100);

SELECT setval('madera.devis_etat_i_devis_etat_id_seq'::regclass, 8);

INSERT INTO madera.gammes(i_gammes_id, v_libelle_gammes) VALUES
(1, 'Standard'),
(2, 'Premium');

SELECT setval('madera.gammes_i_gammes_id_seq'::regclass, 2);

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
(19, 13, 'Phaser', 4, 3),
-- Ouate
(20, 3, 'Ouate minérale', 3 , 100);

SELECT setval('madera.composant_i_composant_id_seq'::regclass, 20);

INSERT INTO madera.module(i_module_id, i_gammes_id, i_module_referentiel_id, v_nom, v_nature_module, v_angle, b_modele) VALUES
(1, 1, 1, 'Mur extérieur standard', 'Mur extérieur', 'Angle sortant', true),
(2, 1, 2, 'Mur intérieur standard', 'Mur intérieur', 'Angle sortant', true),
(3, 1, 4, 'Mur intermédiaire standard', 'Mur intermédiaire', '', true),
(4, 1, 6, 'Toit standard', 'Toit', '', true);

SELECT setval('madera.module_i_module_id_seq'::regclass, 4);

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
(9, 4, 9);

INSERT INTO madera.projet(i_projet_id, i_client_id, v_nom_projet, v_ref_projet, d_date_projet, i_devis_etat_id, f_prix) VALUES
(1, 2, 'Projet Test', 'ref_20191027_2', '2019-10-27', 3, 205365);

SELECT setval('madera.projet_i_projet_id_seq'::regclass, 1);

INSERT INTO madera.projet_module(i_projet_module_id, i_projet_id, i_module_id) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4);

SELECT setval('madera.projet_module_i_projet_module_id_seq'::regclass, 4);

INSERT INTO madera.projet_utilisateurs(i_utilisateur_id, i_projet_id) VALUES
(1, 1),
(2, 1),
(3, 1);

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