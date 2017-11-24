package com.infoshareacademy.mfinance.core.providers.bossadata;


import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.utils.TemporaryFoldersProviderUtil;
import com.infoshareacademy.mfinance.core.utils.UnzipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class RemoteLocationDataFilesProvider implements BossaDataFilesProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteLocationDataFilesProvider.class);
    private final String DATE_PATTERN = "yyyyMMdd";
    private DateTimeFormatter formatter;

    private String currencyZipFolderPath;
    private String fundZipFolderPath;

    private String currencyUnzipTargetPath;
    private String fundUnzipTargetPath;

    private String currencyURL;
    private String fundURL;

    private Path targetPath;

    private Configuration configuration;


    public RemoteLocationDataFilesProvider(Configuration configuration) {

        this.configuration = configuration;

        currencyURL = configuration.getCurrencyUrl().getFileUrl();
        fundURL = configuration.getFundUrl().getFileUrl();
    }

    public boolean saveDataFilesInTempFolders() {
        this.setFilesTargetLocationInTempFolders();
        return getBossaDataFiles();
    }

    public boolean saveDataFilesInExplicitFolders() {
        this.setFilesTargetLocationInExplicitFolders();
        return getBossaDataFiles();
    }

    private void setFilesTargetLocationInTempFolders() {
        currencyZipFolderPath = TemporaryFoldersProviderUtil
                .getCurrencyBackupFolderPath()
                .getFolderPath();

        fundZipFolderPath = TemporaryFoldersProviderUtil
                .getFundBackupFolderPath()
                .getFolderPath();

        currencyUnzipTargetPath = TemporaryFoldersProviderUtil
                .getCurrencyFolderPath()
                .getFolderPath();

        fundUnzipTargetPath = TemporaryFoldersProviderUtil
                .getFundFolderPath()
                .getFolderPath();
    }

    private void setFilesTargetLocationInExplicitFolders() {
        currencyZipFolderPath = configuration
                .getCurrencyBackupFolderPath()
                .getFolderPath();

        fundZipFolderPath = configuration
                .getFundBackupFolderPath()
                .getFolderPath();

        currencyUnzipTargetPath = configuration
                .getCurrencyFolderPath()
                .getFolderPath();

        fundUnzipTargetPath = configuration
                .getFundFolderPath()
                .getFolderPath();
    }

    private boolean getBossaDataFiles() {
        try {

            Path currencyZipFilePath = this.download(currencyURL, currencyZipFolderPath);
            Path fundZipFilePath = this.download(fundURL, fundZipFolderPath);

            UnzipUtil.saveZipFileContent(currencyZipFilePath, currencyUnzipTargetPath);
            UnzipUtil.saveZipFileContent(fundZipFilePath, fundUnzipTargetPath);
            return true;

        } catch (IOException e) {
            LOGGER.error("Failed to update Bossa csv files from remote location (resources unavailable).");
            return false;
        }
    }

    private Path download(String sourceURL, String targetDirectory) throws IOException {
        URL url = new URL(sourceURL);

        try (InputStream stream = url.openStream()) {
            String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
            targetPath = new File(targetDirectory
                    .concat(File.separator)
                    .concat(this.getFileNameWithDate(fileName)))
                    .toPath();

            Files.copy(stream, targetPath, REPLACE_EXISTING);

            LOGGER.info("File saved: source:{}, target location:{}", sourceURL, targetPath);
        } catch (IOException e) {
            LOGGER.error("Failed to download files from remote location:{}", sourceURL);
            throw new IOException();
        }

        return targetPath;
    }

    private String getFileNameWithDate(String defaultFileName) {

        LocalDate localDate = LocalDate.now();
        formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        String formattedDate = localDate.format(formatter);

        return formattedDate
                .concat("_")
                .concat(defaultFileName);
    }
}


