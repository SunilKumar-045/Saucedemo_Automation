pipeline {

    agent any
 
    environment {

        GIT_CREDENTIALS_ID = 'SunilKumar-045' // Replace with your credentials ID

        BRANCH_NAME = 'master' // or 'master' or any other branch

        ECLIPSE_WORKSPACE = 'E:\\Wipro_capstone_Project' // Change to your path

        COMMIT_MESSAGE = 'Automated commit from Jenkins'

    }
 
    stages {

        stage('Checkout from Git') {

            steps {

                checkout([$class: 'GitSCM',

                    branches: [[name: "*/${env.BRANCH_NAME}"]],

                    userRemoteConfigs: [[

                        url: 'https://github.com/your-username/your-repo.git', // Replace with your repo

                        credentialsId: "${env.GIT_CREDENTIALS_ID}"

                    ]]

                ])

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
 
        stage('Configure Git') {

            steps {

                bat """

                git config user.email "sunil@gmail.com"

                git config user.name "Sunil"

                """

            }

        }
 
        stage('Check Git Status') {

            steps {

                bat 'git status'

            }

        }
 
        stage('Commit & Push Changes') {

            steps {

                bat """

                git add .
 
                REM Check if there are changes before committing

                git diff --cached --quiet

                IF %ERRORLEVEL% NEQ 0 (

                    echo Changes detected, committing...

                    git commit -m "${COMMIT_MESSAGE}"

                    git push origin ${BRANCH_NAME}

                ) ELSE (

                    echo No changes to commit.

                )

                """

            }

        }

    }
 
    post {

        success {

            echo 'Push to Git completed (if there were changes).'

        }

        failure {

            echo 'Build failed. Check console output.'

        }

    }

}
 