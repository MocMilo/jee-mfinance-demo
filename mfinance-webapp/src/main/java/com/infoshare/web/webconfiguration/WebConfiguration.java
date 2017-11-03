package com.infoshare.web.webconfiguration;

public class WebConfiguration {

    private String slaveModeAPIServiceTargetURI;
    private boolean isSlave;
    private String defaultAdminAccountLogin;
    

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
}
