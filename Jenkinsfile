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
            steps {
                sh 'mvn clean install' 
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}