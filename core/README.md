# mfinance - core #
         
## Information ##          

Core module is a library containing data model and business rules. It is imported by other application modules. Core functionality is in package 'analyzer'. It provides algorithms typical for financial application (eg. 'Investment revenue', 'Investment indicators' etc.).
For 'in memory' data storage module uses DataContainer object. DataContainer is build from csv files, downloaded from external resource.
Files (csv in ZIP archive) are available at:

* http://bossa.pl/notowania/pliki/eod/omega/
 
##### DEMO MODE
Application by default uses temporary folders to process data files. This behaviour is set in a boolean constant in DataContainerBuilder class.
By default it is set as below (no extra configuration is needed):

```java
IS_DEMO_MODE = true
```    
      
##### EXPLICIT FOLDERS (advanced configuration)
    
To use explicit folder paths (not temporary), set constant in DataContainerBuilder class to:

```java
IS_DEMO_MODE = false
``` 
To use explicit folders add custom folder paths in configuration.json (file location: resources/configuration/configuration.json).
Example configuration is presented in file:
    
```
resources/configuration/configuration-example.json
```


###### _____________________________________
* modules:   [main](../README.md) | core | [cli](../cli/README.md) | [webapp](../webapp/README.md)

