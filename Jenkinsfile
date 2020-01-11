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
                    echo "------------------------------"
                    echo "Script d'installation terminé!"
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