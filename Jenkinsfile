pipeline {
    agent any

    environment {
        APP_ENV = 'dev'
    }

    stages {
        stage('Checkout SCM') {
            steps {
                echo 'Checking out Jenkinsfile repository...'
                checkout scm
            }
        }

        stage('Fetch Repo') {
            steps {
                echo 'Fetching Saucedemo repository...'
                git branch: 'master', url: 'https://github.com/SunilKumar-045/Saucedemo_Automation.git'
            }
        }

        stage('Git Info') {
            steps {
                echo 'Verifying Git commit details...'
                bat 'git log -1 --pretty=format:"Commit: %h%nAuthor: %an%nDate: %ad%nMessage: %s"'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Building the project and running TestNG tests with Maven...'
                bat 'mvn clean test'
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Publishing ExtentReports in Jenkins...'
                publishHTML([
                    reportDir: 'reports',
                    reportFiles: 'ExtentReports.html',
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
