package com.infoshare.mfinance.core.configuration;

import com.infoshare.mfinance.core.models.configuration.Configuration;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


public class ConfigurationProviderTest {


    @Test
    public void shouldReturnConfigurationFromJsonFile() throws Exception {

        ConfigurationProvider configurationProvider = new ConfigurationProvider();
        Configuration config = configurationProvider.getDefaultConfiguration("configuration/ConfigurationTest.json");

        assertThat(config.getCurrencyFolderPath().getFolderPath(), is(equalTo("/home/milo/mfinance/bossa/currencies/")));
        assertThat(config.getCurrencyBackupFolderPath().getFolderPath(), is(equalTo("/home/milo/mfinance/bossa/backup/currencies/")));
        assertThat(config.getFundFolderPath().getFolderPath(), is(equalTo("/home/milo/mfinance/bossa/funds/")));
        assertThat(config.getFundBackupFolderPath().getFolderPath(), is(equalTo("/home/milo/mfinance/bossa/backup/funds/")));
        assertThat(config.getCurrencyFilePaths(), not(equalTo(nullValue())));
        assertThat(config.getFundFilePaths(), not(equalTo(nullValue())));

        assertThat(config.getCurrencyUrl().getFileUrl(), is(equalTo("http://bossa.pl/pub/waluty/omega/omeganbp.zip")));
        assertThat(config.getFundUrl().getFileUrl(), is(equalTo("http://bossa.pl/pub/fundinwest/omega/omegafun.zip")));
    }
}