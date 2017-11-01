package com.infoshare.web.webconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshare.web.webconfiguration.utils.ConfigFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WebConfigurationProvider {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfigurationProvider.class);
    private final String CONFIGURATION_FILE_NAME = "webconfiguration.json";

    private String masterModeAPIServiceTargetURI;
    private String slaveModeAPIServiceTargetURI;
    private boolean isSlave;
    private String defaultAdminAccountLogin;

    public String getMasterModeAPIServiceTargetURI() {
        return masterModeAPIServiceTargetURI;
    }

    public void setMasterModeAPIServiceTargetURI(String masterModeAPIServiceTargetURI) {
        this.masterModeAPIServiceTargetURI = masterModeAPIServiceTargetURI;
    }

    public String getSlaveModeAPIServiceTargetURI() {
        return slaveModeAPIServiceTargetURI;
    }

    public void setSlaveModeAPIServiceTargetURI(String slaveModeAPIServiceTargetURI) {
        this.slaveModeAPIServiceTargetURI = slaveModeAPIServiceTargetURI;
    }

    public boolean isSlave() {
        return isSlave;
    }

    public void setSlave(boolean slave) {
        isSlave = slave;
    }

    public String getDefaultAdminAccountLogin() {
        return defaultAdminAccountLogin;
    }

    public void setDefaultAdminAccountLogin(String defaultAdminAccountLogin) {
        this.defaultAdminAccountLogin = defaultAdminAccountLogin;
    }


    public WebConfigurationProvider getConfiguration() {
        try {
/*            String externalPath = new ConfigurationProvider()
                    .getDefaultConfiguration()
                    .getExternalResourceFilePath();*/
            //fixme read external file path from web resource configuration.json

            String externalPath = "/home/milo/mfinance/";

            Path appModePath = Paths.get(externalPath, CONFIGURATION_FILE_NAME);

            LOGGER.info("Web configuration path {}", appModePath);

            String content = new ConfigFileReader(appModePath).getFileAsString();

            LOGGER.info("content{}", content);

            this.parseJson(content);

            this.isSlave = parseJson(content).isSlave();
            this.slaveModeAPIServiceTargetURI = parseJson(content).getSlaveModeAPIServiceTargetURI();
            this.masterModeAPIServiceTargetURI = parseJson(content).getMasterModeAPIServiceTargetURI();
            this.defaultAdminAccountLogin = parseJson(content).getDefaultAdminAccountLogin();

        } catch (Exception e) {
            LOGGER.error("Failed to load webconfiguration.{} {}", e.getMessage(), e.getStackTrace());
            throw new IllegalStateException();
        }
        return this;
    }

    private WebConfigurationProvider parseJson(String jsonString) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, WebConfigurationProvider.class);
        } catch (IOException e) {
            LOGGER.error("Failed to mapp webconfiguration json to class.{} {}", e.getMessage(), e.getStackTrace());
            throw new IOException();
        }
    }
}
