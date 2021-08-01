# Media-Planner-Test README #

### Purpouse ###

This project is for automating testing of Media Planner

### How do I get set up? ###

* Configuration  
This project uses gradle configuration conventions. You can adjust the configuration for automation overriding the values via command line at runtime.  
  This project also uses Bitbucket Pipelines to allow executing code in bitbucket based on commit events or manually triggering pipeline execution.   
  This is configured exclusively through pipelines.yml and through environment variables passed through bitbucket repository settings.  
  For better scaling this project executes browser events through a remote docker container. That container is not configured locally.  
  
    
* Dependencies
* * Gradle 5.0 > used this guide to install in linux https://linuxize.com/post/how-to-install-gradle-on-ubuntu-18-04/
* * OpenJDK 11.0.x  
* * Download a chromedriver file accordingly to your Chrome browser version at : `/usr/bin/`  
    

* Recommended dev tools
* * VSCode : Java Debugger Plugin, Markdown Plugin, Docker plugin, Lint Plugins, Selenium Java recorder plugin
* * Remote Desktop
* * or IntelliJ     
    
    
* Database configuration - AWS SSO access: https://goodcarrot.jira.com/wiki/spaces/DSO/pages/1025704592/AWS+SSO+Account+setup
* How to run tests using the remote server: `./gradlew test ` 
* How to run tests locally with chromedriver file: `./gradlew clean test -DbrowserModeType=local`
* How to run a single test case + chromedriver : `./gradlew clean test -DbrowserModeType=local --tests className.testName`
* How to run tests In Bitbucket
* How to run tests In Docker locally
* Deployment instructions

### Troubleshooting ###

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* QA Team has ownership  

  
* Carolina Procaccini  
* Alejandro Battagliese   
* Spencer Dawson- Original Author