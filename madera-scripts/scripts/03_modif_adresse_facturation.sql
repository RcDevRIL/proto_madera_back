-- Déplacement du champ b_adresse_facturation
-- Suppression du champ b_adresse_facturation
ALTER TABLE madera.adresse
DROP COLUMN b_adresse_facturation;

-- Ajout du champ b_adresse_facturation
ALTER TABLE madera.client_adresse
ADD COLUMN b_adresse_facturation BOOLEAN DEFAULT false;