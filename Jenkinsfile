pipeline {

    agent any
 
    environment {

        BRANCH_NAME = 'master'

        ECLIPSE_WORKSPACE = 'E:\\Wipro_capstone_Project\\Saucedemo'

        COMMIT_MESSAGE = 'Jenkins: Auto-commit after build'

    }
 
    // Auto-trigger every 5 mins on Git changes

    triggers {

        pollSCM('H H * * *')

    }
 
    stages {

        stage('Checkout from Git') {

            steps {

                git branch: "${env.BRANCH_NAME}",

                    url: 'https://github.com/SunilKumar-045/Saucedemo_Automation.git'

            }

        }
 
        stage('Copy Files from Eclipse Workspace') {

            steps {

                bat """

                echo Copying files from Eclipse workspace...

                xcopy /E /Y /I "${ECLIPSE_WORKSPACE}\\*" "."

                """

            }

        }
 
        stage('Build & Test') {

            steps {

                bat 'mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml'

            }

        }
 
        stage('Commit & Push Changes') {

            steps {

                script {

                    echo 'Checking for changes to push...'

                    withCredentials([usernamePassword(

                        credentialsId: 'sunil045',

                        usernameVariable: 'GIT_USER',

                        passwordVariable: 'GIT_TOKEN')]) {
 
                        bat """

                            git config user.email "jenkins@pipeline.com"
                            
                            echo 'useremailconfirmed'

                            git config user.name "Jenkins CI"
                            
                            echo 'usernameconfirmed'
 
                            git status
                            
                            echo 'status confirm'

                            git add .
 
                            REM Commit only if there are changes

                            git diff --cached --quiet || git commit -m "${COMMIT_MESSAGE}"
 
                            REM Push using token

	                            git push https://%GIT_USER%:%GIT_TOKEN%@github.com/SunilKumar-045/Saucedemo_Automation.git ${BRANCH_NAME}

                        """

                    }

                }

            }

        }

    }
 
    post {

        always {

            // Archive screenshots

            archiveArtifacts artifacts: 'reports/screenshots/*', fingerprint: true
 
            // Publish Cucumber Report

            publishHTML(target: [

                allowMissing: false,

                alwaysLinkToLastBuild: true,

                keepAll: true,

                reportDir: 'reports',

                reportFiles: 'cucumberReport.html',

                reportName: 'cucumberReport'

            ])
 
            // Publish Extent Report

            publishHTML(target: [

                allowMissing: false,

                alwaysLinkToLastBuild: true,

                keepAll: true,

                reportDir: 'reports',

                reportFiles: 'ExtentReports.html',

                reportName: 'Saucedemo_Report'

            ])

        }

    }

}
 