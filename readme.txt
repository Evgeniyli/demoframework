
1) Execute by local machine how to run the test suite

- Clone project to downloads folder from GitLab

- Install plugins:
Lombok plugin
Bath Script Support

- Update paths for to execute run-node1.bat, change your UserName in PC

set SELENIUM_SERVER=C:\Users\UserName\Downloads\testframework\src\main\resources\grid\server\selenium-server-standalone-3.9.1.jar
set HUB_ADDRESS= http://localhost:4444/grid/register/
set NODE_CONFIG=C:\Users\UserName\Downloads\demoframework\src\main\resources\grid\configurations\node1-config.json
set CHROME_DRIVER=C:\Users\UserName\Downloads\demoframework\src\main\resources\grid\drivers\chromedriver.exe
set FIREFOX_DRIVER=C:\Users\UserName\Downloads\demoframework\src\main\resources\grid\drivers\geckodriver.exe
set IE_DRIVER=C:\Users\UserName\Downloads\demoframework\src\main\resources\grid\drivers\IEDriverServer.exe
cd  C:\Users\UserName\Downloads\demoframework\src\main\resources\grid\drivers\

- Run batches
run-hub.bat
run-node1.bat

- Make sure that Selenium Grid is working for http://localhost:4444
- Set parameters in settings.properties:

environment=smoke
browser=chrome
server=local_windows

- Run smoke.xml,  path is : test_framework\src\test\resources\suites\smoke.xml

2) Execute suite by pipeline scripts
add job in Jenkins with parameters:
                    MAVEN_VERSION - MAVEN_VERSION
                    SERVER =  local_windows
                    INSTANCE_NAME =  amazon
                    BROWSER = chrome
                    DXMLFILENAME = smoke.xml
pipeline script in framework: smoke.groovy, path is : test_framework\pipeline\scripts\smoke.groovy
Execute job

3) Execute suit by maven mvn clean compile test -DxmlFileName=src/test/resources/suites/temp_suites/${DxmlFileName} -Pdev2frontend -Pchrome -Plocal_windows