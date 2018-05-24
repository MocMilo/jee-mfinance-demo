# mfinance - webapp #
       
Webapp module is a servlet-jsp web application. It is using [core](../core/README.md) module features to provide financial analysis results for criteria submitted by user. Application has two different user views depending on user role: *admin* and *user*. For user authentication application connects to Google OAuth 2.0 service API. 

Used technologies and APIs:

* Java EE 1.7
* JBoss WildFly 10.1.0 application server
* JPA 1.0.2 with MySQL 6.0
* Docker Compose 1.8.0

## Requirements ##

* Google profile (to login)
* internet connection (for csv data download and user authentication)
* Docker Compose 1.8.0

## Build and deployment ##

1. build and install application using Maven command
 
```bash
$ gradle build
```
2. deploy application in Docker containers using docker-compose (run command inside /docker folder)
 
```bash
$ docker-compose up --build
```
3. welcome page (to login use Google profile):

* http&#58;//localhost:8080/login

## Default administrator account ##

Application after build and deployment creates default administrator account. Default administrator login (e-mail address) is stored inside **webconfiguration.json** file: 

```    
resources/configuration/webconfiguration.json
```

By logging with account specified in **webconfiguration.json** user is redirected into application 'administration panel'. 
To access 'regular-user' view, login with Google profile (e-mail) **not** specified in webconfiguration.json. You can also change *admin role* to *user role* inside User Management panel:
    
* uncheck field: 'isAdmin' in current user's management form

* logout and login - to start a new session in 'regular-user' view 


###### _____________________________________
* modules:   [main](../README.md) | [core](../core/README.md) | [cli](../cli/README.md) | webapp

    
     

     
