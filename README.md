#### mfinance - JEE demo project
    This is a DEMO project with example usage of Java EE technologies.
    It was originally developed during infoShare Academy Java bootcamp. 
    Project was driven by scrum methodology using JIRA tool 
    and Git as version control system.
    
    Release v.2.0 is refactored version of the original project.
    
##### project modules:
    Project is divided into modules for flexibility of development.
    Apache Maven is used for distribution management.
    [webapp] module is deployed on Wildfly server and it is using MySQL database. 
    Web application and database are deployed in Docker-compose containers.
    
##### [core]
    This module contains data model and business rules, shared to other application modules.
    Core functionality is package 'analyzer'. It delivers algorithms typical for financial application 
    (eg.'Investment revenue', 'Investment indicators' etc.)
    Application data is stored 'in memory' using DataContainer object, 
    DataContainer is build from csv files, downloaded from external resource.
    
    For more details about jar build, refer to README.md, stored inside module.
    
##### [cli]
    Module is a 'console application', using [core] module functionalities.
    Application uses 'strategy' design-pattern to control algorithms folow.
    It also uses bean validation to validate parsed arguments.
    
    For more details about build and run, refer to README.md, stored inside module.
##### [webapp]
    Actually, this module is a servlet-jsp web application with database.
    This module is planned to be changed into REST API as a back-end.
    Front-end will be developed as java-script application.
    
    For more details about: actual version, build, deployment etc. refer to README.md, stored inside module.


##### Project build:

##### 1. clone repository

    $ git clone https://github.com/MocMilo/jee-mfinance-demo.git
    
##### 2. checkout actual branch

    $ git fetch origin

    $ git checkout -b mfinance-demo-simplification origin/mfinance-demo-simplification
    
##### 3. build and install application modules using Maven (use root pom.xml)

    $ mvn clean install
    