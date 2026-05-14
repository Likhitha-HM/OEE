pipeline {

    agent any

    environment {

        IMAGE_NAME = "likhithahm/demo-app8:latest"

        NOTIFY_EMAIL = "likhithahm953@gmail.com"
    }

    stages {

        stage('Clone Repository') {

            steps {

                git branch: 'main',
                url: 'https://github.com/Likhitha-HM/OEE.git'
            }
        }

        stage('Build Maven Project') {

            steps {

                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {

            steps {

                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {

            steps {

                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Push Docker Image') {

            steps {

                withDockerRegistry(
                    credentialsId: 'dockerhub',
                    url: ''
                ) {

                    sh "docker push ${IMAGE_NAME}"
                }
            }
        }

        stage('Run Docker Container') {

            steps {

                sh '''
                docker stop survey-app || true
                docker rm survey-app || true

                docker run -d \
                --name survey-app \
                -p 8080:8080 \
                ${IMAGE_NAME}
                '''
            }
        }
    }

    post {

        success {

            mail(
                to: "${NOTIFY_EMAIL}",

                subject: "Jenkins Pipeline SUCCESS",

                body: """
                SUCCESS: Jenkins Pipeline Completed Successfully.

                Project:
                College Campus Survey Application

                GitHub Repository:
                https://github.com/Likhitha-HM/OEE

                Docker Image:
                ${IMAGE_NAME}

                Application deployed successfully.
                """
            )
        }

        failure {

            mail(
                to: "${NOTIFY_EMAIL}",

                subject: "Jenkins Pipeline FAILURE",

                body: """
                FAILURE: Jenkins Pipeline Failed.

                Project:
                College Campus Survey Application

                Check Jenkins console logs for errors.
                """
            )
        }
    }
}
