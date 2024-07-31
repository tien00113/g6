pipeline {

    agent any
    
    stages {
        stage('Clone'){
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/tien00113/g6.git'
            }
        }

        stage('check docker') {
            steps {
                sh label: '', script: 'docker --version'
            }
        }
    }
}