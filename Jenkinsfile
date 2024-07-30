pipeline {

    agent any

    parameters {
        choice(name: 'ACTION', choices: ['Build', 'Remove all'], description: 'Pick something')
    }
    stages {
        stage('Clone'){
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/tien00113/g6.git'
            }
        }
        stage('Build Docker Image') {
            steps {
                // This step should not normally be used in your script. Consult the inline help for details.
                withDockerRegistry(credentialsId: 'dockerhub', url: '') {
                    script {
                        docker.build("tien00113/h2tcoffee", "-f Dockerfile .")
                    }
                }
            }
        }
        // stage('Push Docker Image') {
        //     steps {
        //         withDockerRegistry(credentialsId: 'dockerhub', url: "https://index.docker.io/v1/") {
        //             script {
        //                 docker.image("tien00113/h2tcoffee").push('latest')
        //             }
        //         }
        //     }
        // }
    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}