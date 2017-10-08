package com.infoshare.mfinance.core.configuration;

import com.infoshare.mfinance.core.models.configuration.Configuration;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


public class ConfigurationProviderTest {

    @Test
    public void testGetConfiguration() throws Exception {

       ConfigurationProvider configurationProvider = new ConfigurationProvider();
       Configuration config =  configurationProvider.getConfiguration();

       MatcherAssert.assertThat(config.getCurrencyFolderPath().getFolderPath(), not(equalTo(nullValue())));
       MatcherAssert.assertThat(config.getCurrencyBackupFolderPath().getFolderPath(), not(equalTo(nullValue())));

    }

}