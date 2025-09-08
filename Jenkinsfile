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
        
        stage('Test') {
    		steps {
       			 catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
            	 junit '**/target/surefire-reports/*.xml'
        	}
    	}
	}

		stage('Push Changes') {
            steps {
                echo 'Pushing local changes to GitHub...'
                withCredentials([usernamePassword(credentialsId: 'CapstoneProject', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]) {
                    bat """
                    git config user.email "admin@gmail.com"
                    git config user.name "Admin"

                    git add .
                    git commit -m "Automated commit from Jenkins" || echo "No changes to commit"

                    git push https://${GIT_USER}:${GIT_TOKEN}@github.com/saisai18018/CapstoneProject.git HEAD:main
                    """
                }
			}
		}
        
        
        stage('Publish Reports') {
            steps {
                echo 'Publishing ExtentReports in Jenkins...'
                publishHTML([
                    reportDir: 'reports',    // adjust if your ExtentReports folder differs
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
