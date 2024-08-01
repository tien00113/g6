pipeline {

    agent any

    tools {
        maven 'maven395'
        jdk 'jdk_21'
    }

    environment {
        COMPOSE_FILE = 'docker-compose.yml'
    }
    
    stages {
        stage('Clone'){
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/tien00113/g6.git'
            }
        }

        stage('Mysql setup'){
            steps {
                script {
                    sh 'docker-compose -f ${COMPOSE_FILE} up -d mysql_coffee'
                    sleep(30)
                }
            }
        }

        stage('Build JAR') {
            steps {
                script {
                    sh 'mvn clean package'
                }
            }
        }

        // stage('Build and push image') {
        //     steps {
        //         withDockerRegistry(credentialsId: 'dockerhub', url: '') {
        //             sh label: '', script: 'docker build -t tien00113/g6-app:latest .'
        //             sh label: '', script: 'docker push tien00113/g6-app:latest'
        //         }
        //     }
        // }
    }
}