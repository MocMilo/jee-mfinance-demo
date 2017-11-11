package com.infoshare.web.webconfiguration.api;

public class APIProperties {



    private String masterModeAPIServiceURI;
    private String slaveModeAPIServiceTargetURI;

    public String getMasterModeAPIServiceURI() {
        return masterModeAPIServiceURI;
    }

    public String getSlaveModeAPIServiceTargetURI() {
        return slaveModeAPIServiceTargetURI;
    }

    public APIProperties getWebConfiguration() {

        // TODO Get config from locations
        this.masterModeAPIServiceURI = "http://localhost:8080/financial-app/api";
        this.slaveModeAPIServiceTargetURI = "http://192.168.1.104:8080/financial-app/api";
        return this;
    }
}



