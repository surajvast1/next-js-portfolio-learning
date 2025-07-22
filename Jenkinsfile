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
                script{
                    clone("https://github.com/surajvast1/next-js-portfolio-learning","main")
                }
            }
        }

        stage('Build') {
            steps {
                script{
                    build("portflio","latest","sssurajvast1")
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                script{
                    push("portflio","latest","sssurajvast1")
                }
            }
        }

        stage('Deploy') {
            steps {
                script{
                    deploy()
                }
            }
        }
    }
}
