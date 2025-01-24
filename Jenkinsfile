pipeline {
    agent { label 'Back-agent' }
    tools {
        maven 'maven'
        jdk 'jdk21'
    }
    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'Dev', url: 'https://github.com/Anir-sadiqui/Sirius-back.git/'
            }
        }
        stage('Install') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build') {
            steps {
                sh 'pwd && mvn package'
            }
        }
        stage('Deploy to Prod') {
            steps {
                sh 'echo m6 | sudo -S systemctl restart runBack.service'
            }
        }
       stage('Deploy to Server') {
            steps {
                script {
                    "nohup java -jar agent/workspace/back-jfile/target/Episante-back-1.0-SNAPSHOT.jar > /dev/null 2>&1 & exit"
                        '''
                    }
        }
    }
    }
    }
}