-- Ajout de devis_etat
UPDATE madera.projet
SET i_devis_etat_id = null;
DELETE FROM madera.devis_etat;
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
UPDATE madera.projet
SET i_devis_etat_id = 4;
SELECT setval('madera.devis_etat_i_devis_etat_id_seq'::regclass, 15);