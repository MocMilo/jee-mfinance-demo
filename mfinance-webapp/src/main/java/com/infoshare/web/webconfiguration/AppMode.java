package com.infoshare.web.webconfiguration;


import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class AppMode {

    private boolean isSlave;
    private WebConfigurationProvider webConfigurationProvider = new WebConfigurationProvider();

    public boolean isSlave() {
        return isSlave;
    }
    public AppMode() {
    }

    @PostConstruct
    public void onPostConstruct() {
        this.isSlave = webConfigurationProvider.getWebConfigurationFromResources().isSlave();
    }
}
