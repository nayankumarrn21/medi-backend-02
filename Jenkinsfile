pipeline {
  agent any
  stages {
    stage("build") {
      steps {
        echo "Testing stage build"
      }
    }
    stage('Build and Test') {
      steps {
          sh './mvnw clean test'
      }
    }
  }
}
