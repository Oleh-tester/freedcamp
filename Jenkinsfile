pipeline {
  agent any
  options { ansiColor('xterm'); timestamps() }

  stages {
    stage('Checkout') { steps { checkout scm } }

    stage('Run tests with creds file') {
      steps {
        withCredentials([file(credentialsId: 'freedcamp_creds_properties', variable: 'CREDS_FILE')]) {
          sh """
            ./gradlew clean test \\
              --no-daemon --stacktrace \\
              -PcredsFile="${CREDS_FILE}" \\
              -Dallure.results.directory=build/allure-results
          """
        }
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
        }
      }
    }

    stage('Allure') {
      steps {
        allure([results: [[path: 'build/allure-results']]])
      }
    }
  }
}
