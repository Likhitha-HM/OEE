pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }

    environment {
        IMAGE_NAME = "likhithahm/demo-app8:latest"
        CONTAINER_NAME = "survey-app"
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
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withDockerRegistry([credentialsId: 'dockerhub-credentials', url: '']) {
                    sh 'docker push $IMAGE_NAME'
                }
            }
        }

        stage('Stop Old Container') {
            steps {
                sh '''
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true
                '''
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                docker run -d \
                --name $CONTAINER_NAME \
                -p 9090:8080 \
                $IMAGE_NAME
                '''
            }
        }
    }

    post {

        success {
            emailext(
                subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
                body: """
                Build SUCCESS

                Project: ${JOB_NAME}
                Build Number: ${BUILD_NUMBER}

                Docker Container Running Successfully.

                Build URL:
                ${BUILD_URL}
                """,
                to: "likhithahm953@gmail.com"
            )
        }

        failure {
            emailext(
                subject: "FAILED: ${JOB_NAME} #${BUILD_NUMBER}",
                body: """
                Build FAILED

                Project: ${JOB_NAME}
                Build Number: ${BUILD_NUMBER}

                Check Jenkins Console Output:
                ${BUILD_URL}
                """,
                to: "likhithahm953@gmail.com"
            )
        }
    }
}
