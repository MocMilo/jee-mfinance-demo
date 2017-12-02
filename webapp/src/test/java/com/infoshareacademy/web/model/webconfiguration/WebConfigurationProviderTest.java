package com.infoshareacademy.web.model.webconfiguration;

import com.infoshareacademy.web.services.providers.WebConfigurationProvider;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class WebConfigurationProviderTest {
    private WebConfiguration webConfiguration;

    @Before
    public void init() {
        webConfiguration = new WebConfigurationProvider()
                .getWebConfigurationFromResources();
    }

    @Test
    public void shouldReturnWebConfigurationFromResourcesJsonFile() {
        assertThat(webConfiguration.getDefaultAdminAccountLogin(), not(equalTo(nullValue())));
    }
}