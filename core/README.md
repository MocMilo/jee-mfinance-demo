# mfinance - Java EE demo project #

## [core] ##

         
## Information ##          

[core] module contains data model and business rules, shared to other application modules. Core functionality is package 'analyzer'. It provides algorithms typical for financial application (eg.'Investment revenue', 'Investment indicators' etc.)
For 'in memory' data storage module uses DataContainer object. DataContainer is build from csv files, downloaded from external resource.
Downloaded csv files (in ZIP format) are available at:

    http://bossa.pl/notowania/pliki/eod/omega/
    
DEMO MODE
Application by default uses temporary folders to process data files. This behaviour is set in a boolean constant in DataContainerBuilder class.
By default it is set as below (no extra configuration is needed):

    IS_DEMO_MODE = true
    
      
EXPLICIT FOLDERS (advanced configuration)    
If you want to use explicit folder paths (not temporary), set constant in DataContainerBuilder class to:

    IS_DEMO_MODE = false 
 
To use explicit folders add your custom folder paths in configuration.json file (file is located in resources/configuration/configuration.son)
example configuration is presented in file:
    
    resources/configuration/configuration-example.json
    
