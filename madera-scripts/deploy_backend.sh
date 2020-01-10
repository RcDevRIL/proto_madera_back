#!/bin/sh

echo "--------------------------"
echo "Mise à jour du code source"
echo "--------------------------"

cd /home/dev/proto_madera_back/
git pull

echo "---------------------------------------------------"
echo "Création / Mise à jour de la base de données madera"
echo "---------------------------------------------------"

echo "Attention s'il faut un mot de passe pour votre base de données il faudra le renseigner !"

echo "Suppression de l' ancience base de données"
psql -U postgres madera -h localhost -W -c "DROP SCHEMA madera CASCADE;"

echo "Création de la nouvelle base de données"
psql -U postgres madera -h localhost -W -f ./madera-scripts/dumps/madera_dump.sql

echo "Félicitations, votre base de données est à jour !"
echo "---------------------"
echo "Insertion des données"
echo "---------------------"
psql -U postgres madera -h localhost -W -f ./madera-scripts/dumps/data_dump.sql

echo "La base de données est opérationnel !"

echo "----------------------------------------------------"
echo "Installation de l'application et Exécution des tests"
echo "----------------------------------------------------"

mvn clean install

echo "--------------------------"
echo "Lancement de l'application"
echo "--------------------------"

pgrep java -a > javaPIDs.txt
echo "PIDs détectés:"
cat javaPIDs.txt
tail -n +2 javaPIDs.txt > backendProcessInfos.txt
echo "Infos sur le serveur Madera:"
cat backendProcessInfos.txt
echo "Numéro du PID:"
cut -c 1,2,3,4,5 backendProcessInfos.txt
kill $(cut -c 1,2,3,4,5 backendProcessInfos.txt)
nohup java -jar ./target/*.jar  > /home/dev/maderaserver.log 2>&1 &
rm javaPIDs.txt
rm backendProcessInfos.txt

echo "------------------------------"
echo "Script de déploiement terminé!"
echo "------------------------------"
