package com.infoshareacademy.mfinance.core.providers;

import com.infoshareacademy.mfinance.core.serialization.ConfigurationJSONParser;
import com.infoshareacademy.mfinance.core.utils.ResourcesFileReader;
import com.infoshareacademy.mfinance.core.models.configuration.Configuration;

import java.io.IOException;

public class ConfigurationProvider {
    private String configurationFilePath;
    public ConfigurationProvider(String configurationFilePath) {
        this.configurationFilePath = configurationFilePath;
    }
    /**
     * @return Configuration from json file.
     */
    public Configuration getConfiguration() throws IOException {
        ResourcesFileReader fileReader = new ResourcesFileReader(configurationFilePath);
        String fileContent = fileReader.getFileAsString();
        ConfigurationJSONParser configurationJsonMapper = new ConfigurationJSONParser(fileContent);
        return configurationJsonMapper.getConfigurationFromJson();
    }
}
