package com.infoshareacademy.mfinance.core.providers.bossadata;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.utils.TemporaryFoldersProviderUtil;
import com.infoshareacademy.mfinance.core.utils.UnzipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ResourcesLocationDataFilesProvider implements BossaDataFilesProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesLocationDataFilesProvider.class);
    private static final String CURRENCY_RESOURCE_PATH = "bossademo/currencies/20170827_omeganbp.zip";
    private static final String FUND_RESOURCE_PATH = "bossademo/funds/20170827_omegafun.zip";
    private ClassLoader classLoader = getClass().getClassLoader();

    private Path targetPath;
    private Configuration configuration;

    public ResourcesLocationDataFilesProvider(Configuration configuration) {
        this.configuration = configuration;
    }

    public boolean saveDataFilesInExplicitFolders(){
        try {
            Path currencyZipFilePath = this.copyCsvFiles(CURRENCY_RESOURCE_PATH,
                    configuration.getCurrencyBackupFolderPath().getFolderPath());

            Path fundZipFilePath = this.copyCsvFiles(FUND_RESOURCE_PATH,
                    configuration.getFundBackupFolderPath()
                            .getFolderPath());

            UnzipUtil.saveZipFileContent(currencyZipFilePath,
                    configuration.getCurrencyFolderPath()
                            .getFolderPath());

            UnzipUtil.saveZipFileContent(fundZipFilePath,
                    configuration.getFundFolderPath()
                            .getFolderPath());
            return true;

        } catch (IOException e) {
            LOGGER.error("Failed to save Bossa CSV files from application resources to configuration explicit folders.");
            return false;
        }
    }

    public boolean saveDataFilesInTempFolders() {
        try {
            Path currencyZipFilePath = this.copyCsvFiles(CURRENCY_RESOURCE_PATH, TemporaryFoldersProviderUtil
                    .getCurrencyBackupFolderPath()
                    .getFolderPath());

            Path fundZipFilePath = this.copyCsvFiles(FUND_RESOURCE_PATH, TemporaryFoldersProviderUtil
                    .getFundBackupFolderPath()
                    .getFolderPath());

            UnzipUtil.saveZipFileContent(currencyZipFilePath, TemporaryFoldersProviderUtil
                    .getCurrencyFolderPath()
                    .getFolderPath());

            UnzipUtil.saveZipFileContent(fundZipFilePath, TemporaryFoldersProviderUtil
                    .getFundFolderPath()
                    .getFolderPath());
            return true;

        } catch (IOException e) {
            LOGGER.error("Failed to save Bossa CSV files from application resources to temp folders.");
            return false;
        }
    }

    private Path copyCsvFiles(String sourceFolder, String targetFolder) throws IOException {

        InputStream inputStream = classLoader.getResourceAsStream(sourceFolder);
        String fileName = "ResourcesFileCopy.Zip";

        targetPath = new File(targetFolder
                .concat(File.separator)
                .concat(fileName))
                .toPath();

        Files.copy(inputStream, targetPath, REPLACE_EXISTING);
        inputStream.close();

        LOGGER.info("File saved: source:{}, target location:{}", sourceFolder, targetPath);
        return targetPath;
    }
}
