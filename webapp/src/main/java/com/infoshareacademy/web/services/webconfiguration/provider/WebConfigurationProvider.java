package com.infoshareacademy.web.services.webconfiguration.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.mfinance.core.utils.ResourcesFileReader;
import com.infoshareacademy.web.model.webconfiguration.WebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class WebConfigurationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfigurationProvider.class);
    private static final String WEB_CONFIGURATION_FILE_PATH = "configuration/webconfiguration.json";

    public WebConfiguration getWebConfigurationFromResources() {
        ResourcesFileReader fileReader = new ResourcesFileReader(WEB_CONFIGURATION_FILE_PATH);
        WebConfiguration webConfiguration = new WebConfiguration();
        try {
            String fileContent = fileReader.getFileAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            webConfiguration = objectMapper.readValue(fileContent, WebConfiguration.class);
        } catch (IOException e) {
            LOGGER.error("Failed to create webconfiguration." + e.getMessage());
        }
        return webConfiguration;
    }
}
