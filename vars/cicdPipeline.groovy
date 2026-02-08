def call(Map config = [:]) {

    pipeline {
        agent any

        stages {

            stage('Checkout') {
                steps {
                    git config.repo
                }
            }

            stage('Build') {
                steps {
                    echo "Building application"
                    sh config.buildCmd
                }
            }

            stage('Test') {
                steps {
                    echo "Running tests"
                    sh config.testCmd
                }
            }

            stage('Docker Build') {
                steps {
                    sh "docker build -t ${config.imageName}:${config.tag} ."
                }
            }

            stage('Deploy') {
                steps {
                    echo "Deploy stage placeholder"
                }
            }
        }
    }
}

