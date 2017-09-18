package com.infoshare.core.configuration;

import com.infoshare.core.models.configuration.Configuration;
import com.infoshare.core.models.exceptions.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.infoshare.core.file.path.*;
import com.infoshare.core.file.ResourcesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationProvider.class);
    private final String CONFIGURATION_FILE_PATH = "Configuration.json";
    private Configuration configuration;

    public Configuration getConfiguration() {
        ResourcesFileReader fileReader = new ResourcesFileReader(CONFIGURATION_FILE_PATH);
        try {
            String fileContent = fileReader.getFileAsString();
            ConfigurationJSONMapper configurationJsonMapper = new ConfigurationJSONMapper(fileContent);

            this.configuration = configurationJsonMapper.getConfigurationFromJson();
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
