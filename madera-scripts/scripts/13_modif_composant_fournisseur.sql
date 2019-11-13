ALTER TABLE madera.composant_fournisseur
DROP CONSTRAInT composant_fournisseur_pkey

ALTER TABLE ONLY madera.composant_fournisseur
    ADD CONSTRAINT composant_fournisseur_pkey PRIMARY KEY (i_fournisseur_id, i_composant_id);