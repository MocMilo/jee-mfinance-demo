package com.infoshare.web.webconfiguration;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WebConfigurationProviderTest {

    private WebConfiguration webConfiguration;

    @Before
    public void init() {
        webConfiguration = new WebConfigurationProvider()
                .getWebConfigurationFromResources();
    }
    @Test
    public void shouldReturnWebConfigurationFromResourcesJsonFile() {

        assertThat(webConfiguration.getDefaultAdminAccountLogin(), is(equalTo("dmmtest441@gmail.com")));
        assertThat(webConfiguration.getSlaveModeAPIServiceTargetURI(), is(equalTo("http://localhost:8081/api")));
        assertThat(webConfiguration.isSlave(), is(equalTo(false)));
    }
}