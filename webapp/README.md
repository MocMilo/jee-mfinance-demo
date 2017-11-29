# mfinance - Java EE demo project #

## [webapp] ##


## Requirements ##

* docker-compose 1.8.0
* Google+ profile (to login)
         
## Structure ##          

[webapp] module is a servlet-jsp web application. It is using [core] module features to provide financial analysis results for criteria submitted by user. To authenticate user application uses Google+ Auth 2.0 service. 

used technologies:

* Java EE 1.7
* Wildfly 10 web server
* JPA 1.0.2 with MySQL 6.0
* Docker 1.8.0

All dependencies and plugins configurations are in pom.xml file.

## Build and deployment ##

1. build and install application modules using Maven command

    
    $ mvn clean install
    
2. deploy application in Docker containers using docker-compose (files inside /docker folder)

    
    $ docker/docker-compose up --build

3. welcome page (to login use Google+ profile):

    
    http://localhost:8080/login.jsp



Application after build and deployment creates default administrator account. Default administrator login (e-mail address) is stored inside webconfiguration.json file: 
    
    resources/configuration/webconfiguration.json
    
By logging with account specified in webconfiguration.json user is redirected into application 'administration panel'. 
To access 'regular user' view, login with Google+ profile (e-mail) not specified in webconfiguration.json. You can also change administrator account role to regular-user role inside User Management panel. 
    
checkbox: isAdmin = false 
    
     

     
