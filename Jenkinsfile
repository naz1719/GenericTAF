// Powered by Infostretch 

timestamps {

node () {

	stage ('gTAA Grid - Checkout') {
 	 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '403fb3c6-d9df-4b93-8aff-a0bbb5bc4f64', url: 'https://github.com/naz1719/GenericTAF.git']]]) 
	}
	stage ('gTAA Grid - Build') {
 	
withEnv(["JAVA_HOME=${ tool '"+JDK+"' }", "PATH=${env.JAVA_HOME}/bin"]) { 

// Unable to convert a build step referring to "com.mxstrive.jenkins.plugin.contentreplace.ContentReplaceBuilder". Please verify and convert manually if required.
// Unable to convert a build step referring to "hudson.plugins.gradle.Gradle". Please verify and convert manually if required.
		archiveArtifacts allowEmptyArchive: false, artifacts: '**', caseSensitive: true, defaultExcludes: true, fingerprint: false, onlyIfSuccessful: false 
	}
}
}
}