ALTER TABLE madera.projet
DROP COLUMN i_adresse_livraison;

ALTER TABLE madera.client_adresse
ADD COLUMN b_adresse_livraison boolean NOT NULL DEFAULT false;

ALTER TABLE madera.projet_module
ALTER COLUMN i_projet_module_id TYPE integer;