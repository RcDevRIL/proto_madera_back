-- Augmente la taille du libelle de devis_etat
ALTER TABLE madera.devis_etat
ALTER COLUMN v_devis_etat_libelle TYPE VARCHAR(150);