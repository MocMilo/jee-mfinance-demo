# mfinance - CLI #
                        
CLI module is a 'console application' using [core](../core/README.md) module features. Application has 'strategy' design-pattern implemented to control algorithms flow.
It also uses class-level bean validation to validate parsed arguments. 

## Requirements ##

* OpenJDK Runtime Environment 1.8.0
* internet connection (for csv data download)

## Build and run ##

1. build application with Maven:

 
```bash
$ mvn package
```

2. run application with Java:

 
```bash
$ java -jar target/mfinance-jar-with-dependencies.jar
```    

## CLI commands ##

When CLI application is run with no arguments all available commands and examples of usage are listed. 

*example command (application returns quotations indicators of CHF currency):*    
```bash
$ java -jar target/mfinance-jar-with-dependencies.jar IND CHF
```    


###### _____________________________________
* modules:   [main](../README.md) | [core](../core/README.md) | cli | [webapp](../webapp/README.md)