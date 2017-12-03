package com.infoshareacademy.web.model.webconfiguration;

public class WebConfiguration {
    private String defaultAdminAccountLogin;
    private String clientId;
    private String clientSecret;
    private String callbackURL;

    public String getDefaultAdminAccountLogin() {
        return defaultAdminAccountLogin;
    }

    public void setDefaultAdminAccountLogin(String defaultAdminAccountLogin) {
        this.defaultAdminAccountLogin = defaultAdminAccountLogin;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getCallbackURL() {
        return callbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }
}
