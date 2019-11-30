pipeline {
    agent any
    tools {
        maven 'mvn'
        jdk 'openjdk-11'
    }
    stages {
        stage ('Show environment variable') {
            steps {
                sh '''
                    @echo off
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "JAVA_HOME = ${JAVA_HOME}"
                    echo "WORKSPACE = ${WORKSPACE}"
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