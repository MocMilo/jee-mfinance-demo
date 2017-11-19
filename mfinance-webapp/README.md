#### [mfinance-webapp]

##### Webapp Deployment:
##### 1. clone repository

    $ git clone https://github.com/MocMilo/jee-mfinance-demo.git
    
##### 2. checkout actual branch

    $ git fetch origin

    $ git checkout -b mfinance-demo-simplification origin/mfinance-demo-simplification
    
##### 3. build application using Maven (use root pom.xml)

    $ mvn clean install
    
##### 4. deploy application using Docker containers   

    $ cd docker

    $ docker-compose up --build

-------------------------------------------------------------------------------------
##### 5. welcome page (Google+ auth):
 
    http://localhost:8080/login

##### NOTE! Application by default starts in "DEMO mode". This means no extra configuration is needed (for example csv data files, webconfigurarion.json, smtp.json etc. are loaded from application resources).


    Note! Despite of "DEMO mode" application also supports loading csv files from external location.
    For proper configuration read JAVADOC inside DataContainerBuilder class and directions below:
     
        Set proper <user_name> in Configuration.json path properties (file located in [core] module resources)
     
    
    Note! Example application directories (stored in Configuration.json) :

        /home/<user_name>/mfinance/bossa/currencies/         (default currencies csv location)

        /home/<user_name>/mfinance/bossa/funds/              (default funds csv location)
 
        /home/<user_name>/mfinance/bossa/backup/currencies/  (backup zip files location)

        /home/<user_name>/mfinance/bossa/backup/funds/       (backup zip files location)

     
    
    Note! Other file locations: 
        
        /home/<user_name>/mfinance/smtpconfig.json
        
        /home/<user_name>/mfinance/configuration.json

     
