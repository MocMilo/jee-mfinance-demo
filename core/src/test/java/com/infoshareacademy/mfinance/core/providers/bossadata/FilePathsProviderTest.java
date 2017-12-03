package com.infoshareacademy.mfinance.core.providers.bossadata;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.providers.ConfigurationProvider;
import com.infoshareacademy.mfinance.core.providers.bossadata.locations.FilePathsProvider;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class FilePathsProviderTest {
    private final String CONFIGURATION_TEST_FILE_PATH = "configuration/configuration-test.json";
    private FilePathsProvider filePathsProvider = new FilePathsProvider();
    private Configuration configuration;

    @Before
    public void init() throws IOException {
        configuration = new ConfigurationProvider(CONFIGURATION_TEST_FILE_PATH).getConfiguration();
        RemoteLocationDataFilesProvider remoteLocationDataFilesProvider = new RemoteLocationDataFilesProvider(configuration);
        remoteLocationDataFilesProvider.saveDataFilesInTempFolders();
    }

    @Test
    public void shouldGenerateAbsolutePathsToFilesInTempFolder() {
        assertTrue(filePathsProvider.generateTempCurrencyFilePaths().size() > 0);
        assertTrue(filePathsProvider.generateTempFundFilePaths().size() > 0);
    }
}