@Library("Shared") _
pipeline {
    agent { label "vinod" }

    stages {
        
        stage('Calling a shared library'){
        steps{
            script{
                hello()
            }
         }
        }
    
    
        stage('Code') {
            steps {
                git url: "https://github.com/surajvast1/next-js-portfolio-learning", branch: "main"
            }
        }

        stage('Build') {
            steps {
                sh "docker build -t portflio:latest ."
            }
        }

        stage('Push to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-cred',
                    usernameVariable: 'DOCKER_HUB_USER',
                    passwordVariable: 'DOCKER_HUB_PASS')]) {

                    sh '''
                        set +x
                        echo "$DOCKER_HUB_PASS" | docker login -u "$DOCKER_HUB_USER" --password-stdin
                        docker tag portflio:latest sssurajvast1/portflio:latest
                        docker push sssurajvast1/portflio:latest
                    '''
                }
            }
        }

        stage('Deploy') {
            steps {
                sh "docker compose up -d"
            }
        }
    }
}
