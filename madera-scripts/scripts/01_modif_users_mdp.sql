-- Modification taille champ mot de passe

ALTER TABLE madera.utilisateur
ALTER COLUMN v_password TYPE varchar(255);