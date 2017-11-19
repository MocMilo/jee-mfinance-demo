package com.infoshare.web.webconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshare.web.webconfiguration.utils.ConfigFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WebConfigurationProvider {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfigurationProvider.class);
    private final String WEB_CONFIGURATION_FILE_PATH = "configuration/webconfiguration.json";
    private final String EXTERNAL_PATH = "/home/milo/mfinance/";

    public WebConfiguration getWebConfigurationFromResources() {
        try {

            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream(WEB_CONFIGURATION_FILE_PATH);

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);

            File targetFile = File.createTempFile("tempWebConfiguration", ".json");

            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);

            String content = new ConfigFileReader(Paths.get(targetFile.getAbsolutePath()))
                    .getFileAsString();

            targetFile.deleteOnExit();

            return this.parseJson(content);

        } catch (Exception e) {
            LOGGER.error("Failed to load configuration from resources.{} {}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e.getMessage());
        }
    }

    public WebConfiguration getWebConfigurationFromExternalSource(){
        try {
            Path externalJsonFilePath = Paths.get(EXTERNAL_PATH, WEB_CONFIGURATION_FILE_PATH);

            String content = new ConfigFileReader(externalJsonFilePath)
                    .getFileAsString();

            return this.parseJson(content);

        }catch (IOException e){
            LOGGER.error("Failed to load configuration from external path.{} {}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e.getMessage());
        }
    }

    private WebConfiguration parseJson(String jsonString) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, WebConfiguration.class);
        } catch (IOException e) {
            LOGGER.error("Failed to mapp configuration json to class.{} {}", e.getMessage(), e.getStackTrace());
            throw new IOException();
        }
    }
}
