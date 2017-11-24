package com.infoshareacademy.mfinance.core.serialization;

import java.io.IOException;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.models.exceptions.ConfigurationException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationJSONParser {

    private final String json;
    public ConfigurationJSONParser(String jsonString) {
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
