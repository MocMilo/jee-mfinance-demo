package com.infoshareacademy.web.services.webconfiguration;

import com.infoshareacademy.web.services.webconfiguration.provider.WebConfigurationProvider;
import com.infoshareacademy.web.model.webconfiguration.WebConfiguration;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class WebConfigurationImpl implements WebConfigurationService {
    private WebConfiguration webConfiguration;

    @PostConstruct
    public void onPostConstruct() {
        webConfiguration = new WebConfigurationProvider().getWebConfigurationFromResources();
    }

    @Override
    public WebConfiguration get() {
        return webConfiguration;
    }
}
