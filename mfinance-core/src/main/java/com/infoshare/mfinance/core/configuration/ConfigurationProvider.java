package com.infoshare.mfinance.core.configuration;

import com.infoshare.mfinance.core.file.ResourcesFileReader;
import com.infoshare.mfinance.core.file.path.FilePath;
import com.infoshare.mfinance.core.models.configuration.Configuration;
import com.infoshare.mfinance.core.models.exceptions.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationProvider.class);
    private final String CONFIGURATION_FILE_PATH = "Configuration.json";
    private Configuration configuration;



    public Configuration getConfiguration() {
        /**
         * @return Provides default Configuration from json file.
         */

        return buildConfiguration(CONFIGURATION_FILE_PATH);
    }

    public Configuration getConfiguration(String resourcesFilePath){
        /**
         *@return Provides Configuration from json file.
         *@param resourcesFilePath Explicit resources file path
         */

        return buildConfiguration(resourcesFilePath);
    }

    private Configuration buildConfiguration(String configurationFilePath){
        ResourcesFileReader fileReader = new ResourcesFileReader(configurationFilePath);
        try {
            String fileContent = fileReader.getFileAsString();
            ConfigurationJSONSerializer configurationJsonMapper = new ConfigurationJSONSerializer(fileContent);

            configuration = configurationJsonMapper.getConfigurationFromJson();

            if(configuration.getCurrencyFilePaths().isEmpty()){
                String folderPath = configuration.getCurrencyFolderPath().getFolderPath();
                List<String> fileNames = getFileNameList(folderPath);
                List<FilePath> generatedfilePaths = generateFilePaths(folderPath, fileNames);
                configuration.setCurrencyFilePaths(generatedfilePaths);
            }

            if(configuration.getFundFilePaths().isEmpty()){
                String folderPath = configuration.getFundFolderPath().getFolderPath();
                List<String> fileNames = getFileNameList(folderPath);
                List<FilePath> generatedfilePaths = generateFilePaths(folderPath, fileNames);
                configuration.setFundFilePaths(generatedfilePaths);
            }


        } catch (IOException e) {
            LOGGER.info("Error reading the file: " + e.getMessage());
        } catch (ConfigurationException e) {
            LOGGER.info("Error creating the configuration: " + e.getMessage());
        }
        return configuration;

    }

    private List<String> getFileNameList(String folderPath) {
        List<String> fileNames = new ArrayList<>();

        File[] files = new File(folderPath).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }

    private List<FilePath> generateFilePaths(String folderPath, List<String> fileList) {

        List<FilePath> filePaths = new ArrayList<>();
        for (String fileName : fileList) {
            filePaths.add(new FilePath(folderPath.concat(fileName)));
        }
        return filePaths;
    }
}
