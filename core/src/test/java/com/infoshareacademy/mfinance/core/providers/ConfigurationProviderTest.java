package com.infoshareacademy.mfinance.core.providers;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


public class ConfigurationProviderTest {

    private final String CONFIGURATION_TEST_FILE_PATH = "configuration/configuration-test.json";

    @Test
    public void shouldReturnConfigurationFromJsonFile() throws Exception {

        ConfigurationProvider configurationProvider = new ConfigurationProvider(CONFIGURATION_TEST_FILE_PATH);
        Configuration config = configurationProvider.getConfiguration();

        assertThat(config.getCurrencyFolderPath().getFolderPath(), is(equalTo("/home/milo/mfinance/bossa/currencies/")));
        assertThat(config.getCurrencyBackupFolderPath().getFolderPath(), is(equalTo("/home/milo/mfinance/bossa/backup/currencies/")));
        assertThat(config.getFundFolderPath().getFolderPath(), is(equalTo("/home/milo/mfinance/bossa/funds/")));
        assertThat(config.getFundBackupFolderPath().getFolderPath(), is(equalTo("/home/milo/mfinance/bossa/backup/funds/")));

        assertThat(config.getCurrencyUrl().getFileUrl(), is(equalTo("http://bossa.pl/pub/waluty/omega/omeganbp.zip")));
        assertThat(config.getFundUrl().getFileUrl(), is(equalTo("http://bossa.pl/pub/fundinwest/omega/omegafun.zip")));
    }
}