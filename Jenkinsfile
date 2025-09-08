pipeline {
    agent any

    environment {
        APP_ENV = 'dev'
    }

    stages {
        stage('Clone') {
            steps {
                echo 'Cloning repository...'
                git branch: 'master', url: 'https://github.com/SunilKumar-045/Saucedemo_Automation.git'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Building the project and running TestNG tests with Maven...'
                // Clean + compile + run tests
                bat 'mvn clean test'
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Publishing ExtentReports in Jenkins...'
                publishHTML([
                    reportDir: 'reports/ExtentReports',    // adjust if your ExtentReports folder differs
                    reportFiles: 'index.html',
                    reportName: 'Saucedemo_Report',
                    keepAll: true,
                    allowMissing: false,
                    alwaysLinkToLastBuild: true
                ])
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying to ${env.APP_ENV} environment..."
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed! Please check Jenkins logs and ExtentReport.'
        }
    }
}