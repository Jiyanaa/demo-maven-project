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
					echo "failed to generate artifact.."
				}

			}
		}

		stage("Deploy to staging and checking code quality") {
			parallel {

				stage("Deploy to staging") {
					steps {
						deploy adapters: [tomcat9(credentialsId: 'd1c23a65-3451-4b6b-acf0-b311d0fa51f2', path: '', url: 'http://localhost:8086')], contextPath: null, war: '**/*.war'
					}
					post {
						success {
							echo "an application is deployed to staging environment successfully."
						}
						failure {
						 	echo "failed to deploy an application to staging environment."
						}
					}
				}
				
				stage("Checking code quality") {
					steps {
						bat label: '', script: 'mvn checkstyle:checkstyle'
						checkstyle canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
					}
				}
			}	
		}

		stage("Deploy to production") {
					steps {
						timeout(time: 5, unit: 'HOURS') {
   						 	input 'Do You want to deploy an application to production?'
						}

						deploy adapters: [tomcat9(credentialsId: 'd1c23a65-3451-4b6b-acf0-b311d0fa51f2', path: '', url: 'http://localhost:8087')], contextPath: null, onFailure: false, war: '**/*.war'
					}

					post {
						success {
							echo "An application is deployed to production environment successfully."
						}
						failure {
							echo "Failed to deploy application to production environment."
						}
					}
				}	
	}
}