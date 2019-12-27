#!/bin/sh

echo "---------------------------------------------------"
echo "Création / Mise à jour de la base de données madera"
echo "---------------------------------------------------"

echo "Attention s'il faut un mot de passe pour votre base de données il faudra le renseigner !"

echo "Suppression de l' ancience base de données"
psql -U postgres -h localhost madera -W -c "DROP SCHEMA madera CASCADE;"

echo "Création de la nouvelle base de données"
psql -U postgres -h localhost madera -W -f dumps/madera_dump.sql

echo "Félicitations, votre base de données est à jour !"
echo "---------------------"
echo "Insertion des données"
echo "---------------------"
psql -U postgres -h localhost madera -W -f dumps/data_dump.sql

echo "La base de données est opérationnel !"