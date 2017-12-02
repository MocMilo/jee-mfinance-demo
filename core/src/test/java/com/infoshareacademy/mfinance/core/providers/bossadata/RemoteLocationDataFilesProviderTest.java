package com.infoshareacademy.mfinance.core.providers.bossadata;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.providers.ConfigurationProvider;
import com.infoshareacademy.mfinance.core.utils.TemporaryFoldersProviderUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import static junit.framework.TestCase.assertTrue;

public class RemoteLocationDataFilesProviderTest {
    private final String CONFIGURATION_TEST_FILE_PATH = "configuration/configuration-test.json";
    private RemoteLocationDataFilesProvider remoteLocationDataFilesProvider;
    private Configuration configuration;

    @Before
    public void init() {
        configuration = new ConfigurationProvider(CONFIGURATION_TEST_FILE_PATH)
                .getConfiguration();

        remoteLocationDataFilesProvider = new RemoteLocationDataFilesProvider(configuration);
    }

    @Test
    public void shouldProvideFilesInTempLocation() {
        remoteLocationDataFilesProvider.saveDataFilesInTempFolders();
        File[] testCurrencyPaths = new File(TemporaryFoldersProviderUtil
                .getCurrencyFolderPath()
                .getFolderPath())
                .listFiles();

        File[] testFundPaths = new File(TemporaryFoldersProviderUtil
                .getFundFolderPath()
                .getFolderPath())
                .listFiles();

        assertTrue(testCurrencyPaths.length > 0);
        assertTrue(testFundPaths.length > 0);
    }
}