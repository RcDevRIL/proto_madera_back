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
                    echo "JAVA_HOME = ${JAVA_HOME}"
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "WORKSPACE = ${WORKSPACE}"
                    git --version
                    mvn -version
                    echo "Prepare build for this commit:"
                    git log -1
                '''
            }
        }
        stage ('Build') {
            steps {
                sh 'mvn clean install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}