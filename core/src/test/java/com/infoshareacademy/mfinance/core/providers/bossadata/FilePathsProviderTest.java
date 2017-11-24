package com.infoshareacademy.mfinance.core.providers.bossadata;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.models.locations.path.FilePath;
import com.infoshareacademy.mfinance.core.providers.ConfigurationProvider;
import com.infoshareacademy.mfinance.core.providers.bossadata.locations.FilePathsProvider;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static junit.framework.TestCase.assertTrue;


public class FilePathsProviderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilePathsProviderTest.class);

    private final String CONFIGURATION_TEST_FILE_PATH = "configuration/configuration-test.json";
    private FilePathsProvider filePathsProvider = new FilePathsProvider();
    private Configuration configuration;

    @Before
    public void init() {
        configuration = new ConfigurationProvider(CONFIGURATION_TEST_FILE_PATH).getConfiguration();
        RemoteLocationDataFilesProvider remoteLocationDataFilesProvider = new RemoteLocationDataFilesProvider(configuration);
        remoteLocationDataFilesProvider.saveDataFilesInTempFolders();
    }

    @Test
    public void shouldGenerateAbsolutePathsToFilesInTempFolder() {

        List<FilePath> currencyFilePathList;
        List<FilePath> fundsFilePathList;

        currencyFilePathList = filePathsProvider.generateTempCurrencyFilePaths();
        fundsFilePathList = filePathsProvider.generateTempCurrencyFilePaths();

        LOGGER.info("Number of currency file paths: {}", currencyFilePathList.size());
        LOGGER.info("Number of funds file paths: {}", fundsFilePathList.size());

        for (FilePath item : currencyFilePathList) {
            LOGGER.trace("Generated currency file path: {}", item.getFilePath());
        }

        assertTrue(currencyFilePathList.size() > 0);
    }
}