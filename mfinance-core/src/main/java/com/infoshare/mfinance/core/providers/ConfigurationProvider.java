package com.infoshare.mfinance.core.providers;

import com.infoshare.mfinance.core.serializers.ConfigurationJSONSerializer;
import com.infoshare.mfinance.core.utils.ResourcesFileReader;
import com.infoshare.mfinance.core.models.configuration.Configuration;
import com.infoshare.mfinance.core.models.exceptions.ConfigurationException;
import com.infoshare.mfinance.core.utils.TemporaryFoldersProviderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
            ConfigurationJSONSerializer configurationJsonMapper = new ConfigurationJSONSerializer(fileContent);
            configuration = configurationJsonMapper.getConfigurationFromJson();

        } catch (IOException e) {
            LOGGER.info("Failed to create configuration from file." + e.getMessage());
        } catch (ConfigurationException e) {
            LOGGER.info("Failed to create configuration. " + e.getMessage());
        }
        return configuration;
    }

}
