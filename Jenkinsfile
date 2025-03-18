pipeline{
	agent any
	
	stages{
		
		stage('build'){
			
			steps{
				echo("build the project")
			}
			
		}
		
		stage('Run Unit test'){
			
			steps{
				echo("running the unit tests")
			}
			
		}
		
		stage('Run Integration tests'){
			
			steps{
				echo("running the integration tests")
			}
			
		}
		
		stage('Deploy to QA'){
			
			steps{
				echo("Deploying the code to QA env")
			}
			
		}
		
		stage('Run Regression test suite in QA'){
			
			steps{
				echo("running the regresion tests in QA env")
			}
			
		}
		
			stage('Deploy to UAT'){
			
			steps{
				echo("Deploying the code to UAT env")
			}
			
		}
		
		stage('Run Regression test suite in UAT'){
			
			steps{
				echo("running the regresion tests in UAT env")
			}
			
		}
		
			stage('Deploy to PROD'){
			
			steps{
				echo("Deploying the code to PROD env")
			}
			
		}
		
		stage('Run sanity test suite in PROD'){
			
			steps{
				echo("running the sanity tests in PROD env")
			}
			
		}
	}
}