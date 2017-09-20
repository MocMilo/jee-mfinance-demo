mfinance-core
---
#### Build
Run Maven package command from the project root:
```bash
    mvn package
```
#### Run
In the compiled artifacts directory `target/`, run:
```bash
    # replace $version with the version, ex. 1.0-SNAPSHOT
    java -jar mfinance-$version-jar-with-dependencies.jar
```

#### csv data

files to download available at:
http://bossa.pl/notowania/pliki/eod/omega/


Settings available in resources/Configuration.json file.