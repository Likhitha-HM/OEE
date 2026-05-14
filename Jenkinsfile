pipeline {
    agent any

    tools {
        maven 'Maven8'
        jdk 'JDK21'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Likhitha-HM/OEE.git',
                    credentialsId: 'github-token'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t likhithahm/demo-app8:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {

                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker push likhithahm/demo-app8:latest'
                }
            }
        }

        stage('Stop Old Container') {
            steps {
                sh 'docker stop demo-app8-container || true'
                sh 'docker rm demo-app8-container || true'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 8088:8080 --name demo-app8-container likhithahm/demo-app8:latest'
            }
        }
    }

    
}
