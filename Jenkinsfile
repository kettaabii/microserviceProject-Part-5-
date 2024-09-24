pipeline {
     agent any
      stages {
             stage('Windows Only Stage') {
                 agent { label 'windows' }

    tools {
        maven 'mvn'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub')

        SONARQUBE_CREDENTIALS =credentials('sonar-qube')

    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/kettaabii/MicroservicesProject.git'
            }
        }

        stage('Build & Test Microservices') {
            parallel {
                stage('Build & Test user-service') {
                    steps {
                        dir('user-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test project-service') {
                    steps {
                        dir('project-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test task-service') {
                    steps {
                        dir('task-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test resource-service') {
                    steps {
                        dir('resource-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Package api-gateway-service') {
                    steps {
                        dir('api-gateway-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test eureka-server') {
                    steps {
                        dir('eureka-server') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'sonarqube'


                    dir('user-service') {
                        bat "${scannerHome}/bin/sonar-scanner  -Dsonar.sources=. -Dsonar.login=${sonar-qube}  -Dsonar.java.binaries=target/classes"
                    }


                    dir('project-service') {
                        bat "${scannerHome}/bin/sonar-scanner  -Dsonar.sources=. -Dsonar.login=${sonar-qube} -Dsonar.java.binaries=target/classes"
                    }


                    dir('task-service') {
                        bat "${scannerHome}/bin/sonar-scanner  -Dsonar.sources=. -Dsonar.login=${sonar-qube} -Dsonar.java.binaries=target/classes"
                    }


                    dir('resource-service') {
                        bat "${scannerHome}/bin/sonar-scanner  -Dsonar.sources=. -Dsonar.login=${sonar-qube} -Dsonar.java.binaries=target/classes"
                    }


                    dir('api-gateway-service') {
                        bat "${scannerHome}/bin/sonar-scanner  -Dsonar.sources=. -Dsonar.login=${sonar-qube} -Dsonar.java.binaries=target/classes"
                    }


                    dir('eureka-server') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.sources=. -Dsonar.login=${sonar-qube} -Dsonar.java.binaries=target/classes"
                    }
                }
            }
        }

        stage('Build Docker Images & Push') {
            parallel {
                stage('Build Docker & Push for user-service') {
                    steps {
                        dir('user-service') {
                            script {
                                def dockerImage = docker.build("meleke/user-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for project-service') {
                    steps {
                        dir('project-service') {
                            script {
                                def dockerImage = docker.build("meleke/project-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for task-service') {
                    steps {
                        dir('task-service') {
                            script {
                                def dockerImage = docker.build("meleke/task-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for resource-service') {
                    steps {
                        dir('resource-service') {
                            script {
                                def dockerImage = docker.build("meleke/resource-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for api-gateway-service') {
                    steps {
                        dir('api-gateway-service') {
                            script {
                                def dockerImage = docker.build("meleke/api-gateway-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for eureka-server') {
                    steps {
                        dir('eureka-server') {
                            script {
                                def dockerImage = docker.build("meleke/eureka-server:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                bat 'docker-compose up'
            }
        }
    }
}
