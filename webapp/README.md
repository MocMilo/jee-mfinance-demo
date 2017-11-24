#### [webapp]

##### Webapp deployment:
    
##### 1. deploy application using Docker containers inside /docker folder

    $ cd docker

    $ docker-compose up --build

-------------------------------------------------------------------------------------
##### 2. welcome page:
 
    http://localhost:8080/login

##### NOTE! Application by default starts in "DEMO mode". This means no extra configuration is needed.

    Note! Application after build and first deployment creates default administrator account.
    Default administrator login (e-mail address) is stored inside configuration file: 
    
    resources/configuration/webconfiguration.json
    
    Using this account user is redirected into application 'administrator view'.
    To see 'regular user view', change administrator account role inside User Management panel:
    
    uncheck: isAdmin checkbox
    
     

     
