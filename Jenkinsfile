pipeline {
    agent any
    tools {
        maven 'mvn'
    }
    stages {
        stage ('Show environment variable') {
            steps {
                sh '''
                    set +x
                    echo "JAVA_HOME = ${JAVA_HOME}"
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "WORKSPACE = ${WORKSPACE}"
                    git --version
                    mvn -version
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