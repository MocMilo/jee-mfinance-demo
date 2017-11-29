# mfinance - Java EE demo project #

## [cli] ##
         
         
## Structure ##          

[cli] module is a 'console application' using [core] module features. Application has 'strategy' design-pattern implemented to control algorithms flow.
It also uses bean validation to validate parsed arguments. 

## Build and run ##

1. build application with Maven:

 
    $ mvn package

2. run application with Java:

 
    $ java -jar target/mfinance-jar-with-dependencies.jar
    

example command:
    
    $ java -jar target/mfinance-jar-with-dependencies.jar IND CHF
    
