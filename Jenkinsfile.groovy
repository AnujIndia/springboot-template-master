node('ent_linux_slave') {
    def server = Artifactory.newServer url: 'https://artifactory.allstate.com/artifactory', credentialsId: '33399f9b-7b7d-4640-993d-92ce6bc5079f'
    def uploadSpec = """{
        "files": [
            {
                "pattern": "target/springboot-template*.jar",
                "target": "libs-snapshot/springboot-template/$env.BUILD_NUMBER/"
            }
        ]
    }"""

    stage('checkout') {
        checkout scm
    }

    stage('build') {
        sh '/apps/apache/maven/apache-maven-3.3.9/bin/mvn clean package'
    }

    stage('SonarQube analysis') {
        withSonarQubeEnv {
            sh '/apps/apache/maven/apache-maven-3.3.9/bin/mvn sonar:sonar'
        }
    }

    stage('publish') {
        def buildInfo = server.upload spec: uploadSpec
        server.publishBuildInfo buildInfo
    }
}
