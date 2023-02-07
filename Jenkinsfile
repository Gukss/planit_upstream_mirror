pipeline{
    agent any
    environment {
       BACK_CONTAINER_NAME="planit_back_container"
       BACK_NAME = "planit_back"
    }
    stages {
        stage('Clean'){
            steps{
                script {
                    try{
                        sh "docker stop ${BACK_CONTAINER_NAME}"
<<<<<<< HEAD
                        sleep 1
                        sh "docker rm ${BACK_CONTAINER_NAME}"
=======

                        sleep 1
                        sh "docker rm ${BACK_CONTAINER_NAME}"

>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
                    }catch(e){
                        sh 'exit 0'
                    }
                }
            }
        }
        stage('Build') {
            steps {
                script{
<<<<<<< HEAD
                    sh "sed -i 's/\${DB_USERNAME}/${DB_USERNAME}/' '${WORKSPACE}/back/src/main/resources/application.yml'"
                    sh "sed -i 's/\${DB_PASSWORD}/${DB_PASSWORD}/' '${WORKSPACE}/back/src/main/resources/application.yml'"
                    sh "sed -i 's/\${DB_PORT}/${DB_PORT}/' '${WORKSPACE}/back/src/main/resources/application.yml'"
                    sh "sed -i 's/\${DB_DOMAIN}/${DB_DOMAIN}/' '${WORKSPACE}/back/src/main/resources/application.yml'"
                    sh "docker build -t ${BACK_NAME} ./back/."
=======

                    sed -i "s/\${DB_USERNAME}/${DB_USERNAME}/" "${WORKSPACE}/src/main/resources/application.yml"
                    sed -i "s/\${DB_PASSWORD}/${DB_PASSWORD}/" "${WORKSPACE}/src/main/resources/application.yml"
                    sed -i "s/\${DB_PORT}/${DB_PORT}/" "${WORKSPACE}/src/main/resources/application.yml"
                    sed -i "s/\${DB_DOMAIN}/${DB_DOMAIN}/" "${WORKSPACE}/src/main/resources/application.yml"

                    sh "docker build -t ${BACK_NAME} ./back/."

>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
                }
            }
        }
        stage('Deploy'){
            steps {
                sh "docker run -d --name=${BACK_CONTAINER_NAME} -p 8080:8080 ${BACK_NAME}"
<<<<<<< HEAD
=======
  

>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
                sh "docker image prune --force"
            }
        }
    }
}

