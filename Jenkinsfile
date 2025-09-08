pipeline {
    agent any

    environment {
        APP_ENV = 'dev'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Cloning repository...'
                git branch: 'master', url: 'https://github.com/SunilKumar-045/Saucedemo_Automation.git'
            }
        }

        stage('Build & Run Tests') {
            steps {
                echo 'Building the project and running TestNG suite with Maven...'
                // Run with testng.xml so ExtentReports + ScreenshotUtil work
                bat 'mvn clean test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Publish Extent Report') {
            steps {
                echo 'Publishing ExtentReport in Jenkins...'
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

        stage('Publish Cucumber Report') {
            steps {
                echo 'Publishing Cucumber report...'
                publishHTML([
                    reportDir: 'reports',
                    reportFiles: 'cucumberReport.html',
                    reportName: 'Cucumber Report',
                    keepAll: true,
                    allowMissing: true,
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
