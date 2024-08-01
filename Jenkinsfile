pipeline {

    agent any
    
    stages {
        stage('Clone'){
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/tien00113/g6.git'
            }
        }

        stage('Build JAR') {
            steps {
                script {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build and push image') {
            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: '') {
                    sh label: '', script: 'docker build -t tien00113/g6-app:latest .'
                    sh label: '', script: 'docker push tien00113/g6-app:latest'
                }
            }
        }
    }
}