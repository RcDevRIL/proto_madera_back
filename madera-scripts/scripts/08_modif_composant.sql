-- Ajout de la colonne section (L'épaisseur du composant)
-- TODO Ajout colonne i_longueur et i_largeur ?
ALTER TABLE madera.composant
ADD COLUMN f_section float;

ALTER TABLE madera.composant
DROP COLUMN i_stock_id;