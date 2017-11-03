#### mfinance - JEE demo project
    This is a DEMO project with example usage of Java EE technologies.
    It was originally developed during infoShare Academy Java bootcamp. 
    Project was driven by scrum methodology using JIRA tool, and Git as version control system.
    Release v.2.0 (planned in near future) is an improved version of the original project.
##### modules:
    Project is divided into modules for flexibility of development.
    Apache Maven is used for distribution management.
##### [mfinance-core]
    This module contains model and business rules, shared to other application modules.
    Core functionality is package 'Analyzer'. It delivers algorithms, typical for financial application 
    (eg.'Investment revenue', 'Investment indicators' etc.)
    Application data is stored 'in memory' using DataContainer object, which is based on csv files from external resource.
    For more details about jar build, refer to REDME.md, stored inside module.
##### [mfinance-cli]
    Module is a 'console application', using mfinance-core functionalities.
    Application uses 'strategy' design-pattern to control algorithms folow.
    It also presents 'argument validation' approach in 'console-applications'.
    For more details about build and run, refer to REDME.md, stored inside module.
##### [mfinance-webapp]
    Actually, this module is a servlet-jsp web application with database (deployed in Docker-compose containers).
    It is planned to be changed into REST API as a back-end, and the foront-end will be changed into java-script based application.
    For more details about: actual version, build, deployment etc. refer to REDME.md, stored inside module.
