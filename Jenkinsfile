pipeline {
  agent any

  options {
    ansiColor('xterm')
    timestamps()
    buildDiscarder(logRotator(numToKeepStr: '30'))
    timeout(time: 45, unit: 'MINUTES')
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Run tests') {
      steps {
        withCredentials([file(credentialsId: 'freedcamp_creds_properties', variable: 'CREDS_FILE')]) {
          sh 'chmod +x gradlew'

          catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
            sh """
              ./gradlew clean test \\
                --no-daemon --stacktrace \\
                -PcredsFile="${CREDS_FILE}" \\
                -Dallure.results.directory=build/allure-results
            """
          }
        }
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'

          sh 'ls -la build/allure-results || true'

          allure([
            includeProperties: false,
            jdk: '',
            results: [[path: 'build/allure-results']]
          ])
        }
      }
    }
  }
}