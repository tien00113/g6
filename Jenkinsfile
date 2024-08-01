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
        stage('Build JAR and build image') {
            steps {
                script {
                    sh 'mvn clean package -DskipTests'
                    sh 'docker build -t g6-app:latest .'
                }
            }
        }
        stage('Push image to dockerhub') {
            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: '') {
                    sh label: '', script: 'docker tag g6-app:latest tien00113/g6-app:latest'
                    sh label: '', script: 'docker push tien00113/g6-app:latest'
                }
            }
        }
    }
}