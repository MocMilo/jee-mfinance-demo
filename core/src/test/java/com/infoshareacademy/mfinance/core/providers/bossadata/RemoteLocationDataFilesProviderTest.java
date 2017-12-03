package com.infoshareacademy.mfinance.core.providers.bossadata;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.providers.ConfigurationProvider;
import com.infoshareacademy.mfinance.core.utils.TemporaryFoldersProviderUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class RemoteLocationDataFilesProviderTest {
    private static final String CONFIGURATION_TEST_FILE_PATH = "configuration/configuration-test.json";
    private RemoteLocationDataFilesProvider remoteLocationDataFilesProvider;

    @Before
    public void init() throws IOException {
        Configuration   configuration = new ConfigurationProvider(CONFIGURATION_TEST_FILE_PATH)
                .getConfiguration();

        remoteLocationDataFilesProvider = new RemoteLocationDataFilesProvider(configuration);
    }

    @Test
    public void shouldProvideFilesInTempLocation() throws IOException{
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