package com.infoshare.core.configuration;

import java.io.IOException;

import com.infoshare.core.models.configuration.Configuration;
import com.infoshare.core.models.exceptions.ConfigurationException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConfigurationJSONMapper {

    private final String json;
    public ConfigurationJSONMapper(String jsonString) {
        this.json = jsonString;
    }

    public Configuration getConfigurationFromJson() throws ConfigurationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Configuration.class);
        } catch (IOException e) {
            throw new ConfigurationException(e);
        }
    }
}
