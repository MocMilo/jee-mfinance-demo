package com.infoshareacademy.mfinance.core.providers;

import com.infoshareacademy.mfinance.core.serialization.ConfigurationJSONParser;
import com.infoshareacademy.mfinance.core.utils.ResourcesFileReader;
import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.models.exceptions.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationProvider.class);
    private Configuration configuration;
    private String configurationFilePath;

    public ConfigurationProvider(String configurationFilePath) {
        this.configurationFilePath = configurationFilePath;
    }

    public Configuration getConfiguration() {

        /**
         * @return Provides Configuration from json file locations.
         *
         */

        return this.getConfigurationFromFile();
    }

    private Configuration getConfigurationFromFile() {

        ResourcesFileReader fileReader = new ResourcesFileReader(configurationFilePath);
        try {
            String fileContent = fileReader.getFileAsString();
            ConfigurationJSONParser configurationJsonMapper = new ConfigurationJSONParser(fileContent);
            configuration = configurationJsonMapper.getConfigurationFromJson();
        } catch (ConfigurationException e) {
            LOGGER.info("Failed to create configuration. " + e.getMessage());
        }
        return configuration;
    }

}
