pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/SunilKumar-045/Saucedemo_Automation.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test -DsuiteXmlFile=Saucedemo/testng.xml'
            }
        }
    }
}
