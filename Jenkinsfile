pipeline {
    agent any
    tools {
        maven 'mvn'
    }
    stages {
        stage ('Prepare build') {
            steps {
                sh '''
                    set +x
                    set -e
                    echo "Prepare build for this commit:"
                    git log -1
                    echo "Copying properties to workspace..."
                    cp /home/dev/proto_madera_back/src/main/resources/madera.properties ${WORKSPACE}/src/main/resources/madera.properties
                    echo "Preparation Stage Done."                
                '''
            }
        }
        stage ('Build') {
            steps { // le tail -n +2 récupère la deuxieme liste. Si le PID du serveur madera est plus petit que le PID de jenkins alors ce script eteindra Jenkins!!
                sh '''
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
                    cut -d' ' -f 1 backendProcessInfos.txt
                    sudo kill $(cut -d' ' -f 1 backendProcessInfos.txt)
                    nohup java -jar ./target/*.jar  > /var/lib/jenkins/maderalogs/maderaserver.log 2>&1 &
                    rm javaPIDs.txt
                    rm backendProcessInfos.txt
                    echo "------------------------------"
                    echo "Script de déploiement terminé!"
                    echo "------------------------------"
                ''' 
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}