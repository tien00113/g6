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
        stage('init db') {
            steps {
                sh 'docker-compose -f ${COMPOSE_FILE} up -d mysql_coffee'
                sh 'docker-compose -f ${COMPOSE_FILE} exec -T mysql_coffee sh -c "until mysqladmin ping -h mysql_coffee -u root -p12345678 --silent; do echo Waiting for database; sleep 5; done"'
            }
        }
        stage('Build JAR and build image') {
            steps {
                script {
                    sh 'mvn clean package'
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