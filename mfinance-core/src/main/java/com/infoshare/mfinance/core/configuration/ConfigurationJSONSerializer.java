package com.infoshare.mfinance.core.configuration;

import java.io.IOException;

import com.infoshare.mfinance.core.models.configuration.Configuration;
import com.infoshare.mfinance.core.models.exceptions.ConfigurationException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationJSONSerializer {

    private final String json;
    public ConfigurationJSONSerializer(String jsonString) {
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
