pipeline {
	agent any

	tools {
  		maven 'Maven'
	}

	stages {
		stage("Package") {
			steps {
				bat label: '', script: 'mvn clean package'
			}
			post { 
				success {
					echo "Artifact generated successfully."
					echo "Archiving an artifact..."
					archiveArtifacts '**/*.war'
				}
				failure {
					echo "Something went wrong..."
				}

			}
		}

		stage("Deploy to staging") {
			steps {
				build 'maven-hello-world-deploy-to-staging'
			}
		}

		/*stage("Checking code quality") {
			steps {
				build 'maven-hello-world-checkstyle'
			}
		}*/

		stage("Deploy to production") {
			steps {
				timeout(time: 5, unit: 'HOURS') {
    				input 'Deploy an application to production'
				}
				build 'maven-hello-world-deploy-to-production'
			}
			post {
				success {
					echo "an application is deploy to production successfully."
				}
				failure {
					echo "an application is failed to deploy."
				}
			}
		}
	}
}