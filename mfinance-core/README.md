#### [mfinance-core]

##### Build
Run Maven package command from the project root:

    $ mvn package


##### csv data

Note! Application mode is a boolean constant stored in DataContainerBuilder class.

    IS_DEMO_MODE = true

In Demo mode application uses csv files stored in Zip files located in app resources:

    resources/bossademo/currencies/omeganbp.zip
    resources/bossademo/funds/omegafun.zip

CSV files used by application are available at:

    http://bossa.pl/notowania/pliki/eod/omega/
    