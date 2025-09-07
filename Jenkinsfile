pipeline {
    agent any

    tools {
        maven 'Maven3.9.9'   // Configure this name in Jenkins Global Tool Configuration
        jdk 'JDK21'       // Configure JDK in Jenkins and match the name
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/SunilKumar-045/Saucedemo_Automation.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test -DsuiteXmlFile=testng.xml"
            }
        }

        stage('Reports') {
            steps {
                // Archive TestNG reports
                junit '**/test-output/testng-results.xml'
                
                // Archive ExtentReports (if generated inside reports folder)
                archiveArtifacts artifacts: 'reports/**/*', fingerprint: true

                // Archive Screenshots
                archiveArtifacts artifacts: 'screenshots/**/*', fingerprint: true
            }
        }
    }

    post {
        always {
            echo "Cleaning workspace..."
            deleteDir()
        }
        failure {
            mail to: 'csunilk2002@gmail.com',
                 subject: "Saucedemo Pipeline Failed",
                 body: "Check Jenkins for details."
        }
    }
}
