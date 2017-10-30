###mfinance-core

#### Build
Run Maven package command from the project root:

    $ mvn package


#### csv data

CSV files used by application are available at:

    http://bossa.pl/notowania/pliki/eod/omega/

In Demo mode application uses csv files stored in Zip file in location:

    resources/bossademo/currencies
    resources/bossademo/funds
  

In production mode csv files location setup is stored in

    resources/Configuration.json file.


Note! Application mode is a boolean constant stored in DataContainerBuilder class.

    IS_DEMO_MODE = true
    