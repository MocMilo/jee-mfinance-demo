package com.infoshare.web.webconfiguration;


import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        this.isSlave = webConfigurationProvider.getConfiguration().isSlave();
    }
}
