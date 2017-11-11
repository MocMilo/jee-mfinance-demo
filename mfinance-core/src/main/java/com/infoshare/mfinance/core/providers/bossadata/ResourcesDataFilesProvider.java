package com.infoshare.mfinance.core.providers.bossadata;

import com.infoshare.mfinance.core.utils.TemporaryFoldersProviderUtil;
import com.infoshare.mfinance.core.utils.UnzipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ResourcesDataFilesProvider implements IBossaDataProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesDataFilesProvider.class);
    private static final String CURRENCY_DEMO_RESOURCE_PATH = "bossademo/currencies/20170827_omeganbp.zip";
    private static final String FUND_DEMO_RESOURCE_PATH = "bossademo/funds/20170827_omegafun.zip";
    private ClassLoader classLoader = getClass().getClassLoader();

    private Path targetPath;

    public boolean saveFilesInTemp() {
        try {
            Path currencyZipFilePath = this.copyCsvFilesToTempFolder(CURRENCY_DEMO_RESOURCE_PATH, TemporaryFoldersProviderUtil
                    .getCurrencyBackupFolderPath()
                    .getFolderPath());

            Path fundZipFilePath = this.copyCsvFilesToTempFolder(FUND_DEMO_RESOURCE_PATH, TemporaryFoldersProviderUtil
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
            LOGGER.error("Failed to save Bossa CSV files from application resources.");
            return false;
        }
    }

    private Path copyCsvFilesToTempFolder(String sourceFolder, String targetFolder) throws IOException {

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
