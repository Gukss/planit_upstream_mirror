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
                        sleep 1
                        sh "docker rm ${BACK_CONTAINER_NAME}"
                    }catch(e){
                        sh 'exit 0'
                    }
                }
            }
        }
        stage('Build') {
            steps {
                script{
                    sh "sed -i 's/\\${DB_USERNAME}/${DB_USERNAME}/' '${WORKSPACE}/back/src/main/resources/application.yml'"
                    sh "sed -i 's/\\${DB_PASSWORD}/${DB_PASSWORD}/' '${WORKSPACE}/back/src/main/resources/application.yml'"
                    sh "sed -i 's/\${DB_PORT}/${DB_PORT}/' '${WORKSPACE}/back/src/main/resources/application.yml'"
                    sh "sed -i 's/\${DB_DOMAIN}/${DB_DOMAIN}/' '${WORKSPACE}/back/src/main/resources/application.yml'"
                    sh "docker build -t ${BACK_NAME} ./back/."
                }
            }
        }
        stage('Deploy'){
            steps {
                sh "docker run -d --name=${BACK_CONTAINER_NAME} -p 8080:8080 ${BACK_NAME}"
                sh "docker image prune --force"
            }
        }
    }
}
