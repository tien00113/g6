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
                    sh 'docker run -d --name mysql_coffee -e MYSQL_ROOT_PASSWORD=12345678 -e MYSQL_DATABASE=db_coffee -p 3308:3306 -v coffee-mysql-data:/var/lib/mysql -v ./mysql:/docker-entrypoint-initdb.d --network java-app mysql:8.0.36 --default-authentication-plugin=mysql_native_password'
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