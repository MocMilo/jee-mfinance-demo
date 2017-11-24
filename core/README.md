#### [core]

##### Build
Run Maven package command from the project root:

    $ mvn package


##### csv data

Application downloads csv files in Zip format.
CSV files used by application are available at:

    http://bossa.pl/notowania/pliki/eod/omega/
    
Application needs internet connection to use most recent data.    
If internet connection is unavailable application uses data from it's own resources
(in this case data is actual up to day: 2017-08-27).
Zip files are located at:

    resources/bossademo/currencies/20170827_omeganbp.zip
    resources/bossademo/funds/20170827_omegafun.zip
   

**************************************************************************************
DEMO MODE

Note! Application by default uses temporary folders to process data files.
This behaviour is set in a boolean constant in DataContainerBuilder class.
By default it is set as below (no extra configuration is needed):

    IS_DEMO_MODE = true
    

**************************************************************************************    
EXPLICIT FOLDERS (advanced configuration)    

If you want to use explicit folder paths (not temporary), 
set constant in DataContainerBuilder class to:

    IS_DEMO_MODE = false 
 
Note! To use explicit folders add your custom folder paths in configuration.json file.
(file is located in resources/configuration/configuration.json)

example configuration is presented in file:
    
    resources/configuration/configuration-example.json
    


    