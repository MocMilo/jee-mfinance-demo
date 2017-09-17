package com.infoshare.core.configuration;

import java.io.IOException;

import com.infoshare.core.models.exceptions.AppConfigurationProviderException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONMapper {

    private final String jsonString;

    public JSONMapper(String jsonString) {
        this.jsonString = jsonString;
    }

    public ConfigurationProvider getAppConfigurationFromJson() throws AppConfigurationProviderException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, ConfigurationProvider.class);
        } catch (IOException e) {
            throw new AppConfigurationProviderException(e);
        }
    }


}
