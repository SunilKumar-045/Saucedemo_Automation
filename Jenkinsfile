pipeline {
    agent any

    tools {
        maven 'Maven3.9.9'   // Match the name you configured in Jenkins
        jdk 'JDK21'          // Match the JDK name in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/SunilKumar-045/Saucedemo_Automation.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test -DsuiteXmlFile=testng.xml"
            }
        }

        stage('Reports') {
            steps {
                junit '**/test-output/testng-results.xml'
                archiveArtifacts artifacts: 'reports/**/*', fingerprint: true
                archiveArtifacts artifacts: 'screenshots/**/*', fingerprint: true
            }
        }
    }

    post {
        always {
            echo "Cleaning workspace..."
            deleteDir()
        }
    }
}
