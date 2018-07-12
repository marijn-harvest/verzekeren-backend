pipeline {
    agent any
    tools { 
        maven 'Maven 3.5.4' 
        jdk 'jdk8' 
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                checkout scm
                sh 'mvn clean package'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage('Deploy') {
            when {
                expression {
                    currentBuild.result == null || currentBuild.result == 'SUCCESS' 
                }
            }
            steps {
                echo 'Deploying....'
                def dockerImage = docker.build("quay.io/marijn_harvest/verzekeren_backend")
                dockerImage.push('latest')
            }
        }
    }
}