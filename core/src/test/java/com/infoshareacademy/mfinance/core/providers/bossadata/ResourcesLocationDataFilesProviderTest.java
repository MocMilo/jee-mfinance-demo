package com.infoshareacademy.mfinance.core.providers.bossadata;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.providers.ConfigurationProvider;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ResourcesLocationDataFilesProviderTest {

    private final String CONFIGURATION_TEST_FILE_PATH = "configuration/configuration-test.json";

    @Test
    public void shouldCopyZipFilesFromAppResourcesToTempFolder() {
        ConfigurationProvider configurationProvider = new ConfigurationProvider(CONFIGURATION_TEST_FILE_PATH);
        Configuration config = configurationProvider.getConfiguration();

        ResourcesLocationDataFilesProvider filesProvider = new ResourcesLocationDataFilesProvider(config);
        boolean isSuccess = filesProvider.saveDataFilesInTempFolders();
        assertTrue(isSuccess);
    }
}