package com.infoshare.mfinance.core.providers.bossadata;

import com.infoshare.mfinance.core.models.configuration.Configuration;
import com.infoshare.mfinance.core.providers.ConfigurationProvider;
import com.infoshare.mfinance.core.providers.bossadata.RemoteDataFilesProvider;
import com.infoshare.mfinance.core.utils.TemporaryFoldersProviderUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoteDataFilesProviderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteDataFilesProviderTest.class);
    private final String CONFIGURATION_TEST_FILE_PATH = "configuration/configuration-test.json";
    private RemoteDataFilesProvider remoteDataFilesProvider;
    private Configuration configuration;

    @Before
    public void init() {
        configuration = new ConfigurationProvider(CONFIGURATION_TEST_FILE_PATH)
                .getConfiguration();

        remoteDataFilesProvider = new RemoteDataFilesProvider(configuration);
    }

    @Test
    public void shouldProvideFilesInTempLocation() {
        remoteDataFilesProvider.saveFilesInTemp();

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

        for (File item : testCurrencyPaths) {
            LOGGER.trace(item.getAbsolutePath());
        }

        for (File item : testFundPaths) {
            LOGGER.trace(item.getAbsolutePath());
        }
    }

}