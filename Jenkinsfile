pipeline {
    agent any // Or specify a label for a Windows agent if you have one configured, e.g., agent { label 'windows-node' }

    stages {
        stage('Checkout Code') {
            steps {
                // Replace with your SCM details (Git, SVN, etc.)
                git 'https://github.com/SunilKumar-045/Saucedemo_Automation.git'
            }
        }

        stage('Build and Test') {
            steps {
                // Execute Maven clean install to build and run unit/integration tests
                // Use 'bat' for Windows commands
                bat 'mvn clean install'
            }
        }
    }