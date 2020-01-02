-- Suppression de la contrainte de madera.projet
ALTER TABLE ONLY madera.projet
DROP CONSTRAINT fk_client;