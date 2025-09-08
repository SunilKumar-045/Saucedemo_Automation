pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/SunilKumar-045/Saucedemo_Automation.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'test-output/', fingerprint: true
                    publishHTML([
                        reportDir: 'reports',
                        reportFiles: 'Saucedemo_Report.html',
                        reportName: 'Saucedemo Report',
                        keepAll: true, 
                        allowMissing: false,
                        alwaysLinkToLastBuild: true
                    ])
                }
            }
        }
    }
}