pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub')
        SONARQUBE_CREDENTIALS = credentials('sonar-qube')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/kettaabii/MicroservicesProject.git'
            }
        }

        stage('Build & Test Microservices') {
            steps {
                script {
                    def services = ['user-service', 'project-service', 'task-service', 'resource-service', 'api-gateway-service', 'eureka-server']
                    services.each { service ->
                        dir(service) {
                            powershell 'mvn clean package'
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'sonarqube'
                    def services = ['user-service', 'project-service', 'task-service', 'resource-service', 'api-gateway-service', 'eureka-server']

                    services.each { service ->
                        dir(service) {
                            powershell """
                                ${scannerHome}/bin/sonar-scanner.bat -Dsonar.sources=. -Dsonar.login=${SONARQUBE_CREDENTIALS} -Dsonar.java.binaries=target/classes
                            """
                        }
                    }
                }
            }
        }

        stage('Build Docker Images & Push') {
            steps {
                script {
                    def services = ['user-service', 'project-service', 'task-service', 'resource-service', 'api-gateway-service', 'eureka-server']
                    services.each { service ->
                        dir(service) {
                            powershell """
                                docker build -t meleke/${service}:${env.TAG_VERSION ?: 'latest'} .
                                docker login -u ${DOCKERHUB_CREDENTIALS_USR} -p ${DOCKERHUB_CREDENTIALS_PSW}
                                docker push meleke/${service}:${env.TAG_VERSION ?: 'latest'}
                                docker logout
                            """
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            powershell 'Remove-Item -Path * -Recurse -Force'
        }
        failure {
            echo 'The Pipeline failed :('
        }
    }
}