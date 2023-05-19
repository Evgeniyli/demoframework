pipeline {
    options {
        skipDefaultCheckout true
        disableConcurrentBuilds()
        buildDiscarder(
                logRotator(
                        numToKeepStr: '30'
                )
        )
    }
    agent { label 'master' }
    stages {
        stage('Reading environment variables defined in properties file') {
            steps {
                script {
                    load "${WORKSPACE}@script\\pipeline\\properties\\${PROPERTIES_FILE}"
                    echo "------Environment variables defined in properties file------"
                    echo "${env.AGENT_LABEL}"
                    echo "${env.MAVEN_VERSION}"
                    echo "${env.SERVER}"
                    echo "${env.INSTANCE_NAME}"
                    echo "${env.BROWSER}"
                    echo "${env.DXMLFILENAME}"
                    echo "------Environment variables defined in properties file------"
                }
            }
        }

        stage('smoke test') {
            agent { label "${env.AGENT_LABEL}" }
            steps {
                script {
                    try {
                        withCredentials([string(credentialsId: 'linuxservice', variable: 'linuxservice')]) {
                            withMaven(
                                    maven: "${MAVEN_VERSION}",
                                    options: [junitPublisher(disabled: true)]) {
                                sh('mvn clean compile test -DxmlFileName=src/test/resources/suites/temp_suites/${DxmlFileName} -P${INSTANCE_NAME} -P{BROWSER} -P${SERVER}')
                            }
                        }
                    } catch (error) {
                        allure commandline: 'allure_latest', includeProperties: true, jdk: '', report: 'allure-report', results: [[path: 'allure-results']]
                        throw error
                    }
                }
            }
        }
        stage('allure-results') {
            agent { label "${env.AGENT_LABEL}" }
            steps {
                script {
                    allure commandline: 'allure_latest', includeProperties: true, jdk: '', report: 'allure-report', results: [[path: 'allure-results']]
                    manager.build.@result = hudson.model.Result.SUCCESS
                }
            }
        }
    }
    post {
        always {
            script {
                emailext(
                        body: """
------------------------------------------------------------------   
                         TESTS
------------------------------------------------------------------
                        RESULTS
======================================
\${BUILD_LOG_REGEX,regex="^.*Tests on Chrome.*\$",linesBefore=0,linesAfter=1,showTruncatedLines=false}
======================================
BUILD ${currentBuild.currentResult}
------------------------------------------------------------------
\${BUILD_LOG_REGEX, regex = ".*Total time.*",linesBefore=0,linesAfter=1,showTruncatedLines=false}
======================================
\${BUILD_LOG_REGEX, regex ="^.*Failed test.*\$",linesBefore=0,linesAfter=0,showTruncatedLines=false}""",
                        mimeType: 'default',
                        subject: "${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - ${currentBuild.currentResult}",
                        to: "Yevhen.Lyzohubov@testemail.com",
                        from: 'testcompany.com'
                )
            }
        }
    }
}

