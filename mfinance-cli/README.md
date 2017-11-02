#### [mfinance-cli]

##### Build
Run Maven package command from the project root:
 
    $ mvn package
 
##### Run
In the compiled artifacts directory `target/`, run:
 
    $ java -jar mfinance-jar-with-dependencies.jar
 
##### Run with example arguments:
To get 'CHF investment indicators' analysis, run:
 
    $ java -jar mfinance-jar-with-dependencies.jar IND CHF
 
To get 'USD capital investment revenue' analysis, run:
 
    $ java -jar mfinance-jar-with-dependencies.jar IVR USD 1000.00 2017-01-11 2017-03-15
 

