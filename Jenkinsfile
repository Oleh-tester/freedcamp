pipeline {
  agent any

  options {
    timestamps()
    buildDiscarder(logRotator(numToKeepStr: '30'))
    timeout(time: 45, unit: 'MINUTES')
  }

  stages {
    stage('Checkout') {
      steps {
      deleteDir()
      checkout scm }
    }

    stage('Run tests') {
      steps {
        withCredentials([file(credentialsId: 'freedcamp_creds_properties', variable: 'CREDS_FILE')]) {
          sh 'chmod +x gradlew || true'

          catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
            sh """
              ./gradlew test --tests "*AuthTests" \\
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

          sh 'echo "--- allure-results:" && ls -la build/allure-results || true'

          script {
            if (fileExists('build/allure-results') && sh(script: 'test -n "$(ls -A build/allure-results 2>/dev/null)"', returnStatus: true) == 0) {
              catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                allure([
                  includeProperties: false,
                  jdk: '',
                  results: [[path: 'build/allure-results']]
                ])
              }
            } else {
              echo '⚠️ Allure: No results found, skipping Allure report generation.'
            }
          }
        }
      }
    }
  }
}