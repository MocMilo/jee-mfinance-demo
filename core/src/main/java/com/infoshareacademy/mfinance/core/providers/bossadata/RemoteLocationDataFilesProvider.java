package com.infoshareacademy.mfinance.core.providers.bossadata;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.utils.TemporaryFoldersProviderUtil;
import com.infoshareacademy.mfinance.core.utils.ZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class RemoteLocationDataFilesProvider implements DataFilesProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteLocationDataFilesProvider.class);
    private final String DATE_PATTERN = "yyyyMMdd";
    private DateTimeFormatter formatter;

    private String currencyZipFolderPath;
    private String fundZipFolderPath;

    private String currencyUnzipTargetPath;
    private String fundUnzipTargetPath;

    private String currencyURL;
    private String fundURL;

    private URL url;
    private Path targetPath;

    private Configuration configuration;

    public RemoteLocationDataFilesProvider(Configuration configuration) {
        this.configuration = configuration;
        currencyURL = configuration.getCurrencyUrl().getFileUrl();
        fundURL = configuration.getFundUrl().getFileUrl();
    }

    public void saveDataFilesInTempFolders() {
        this.setFilesTargetLocationInTempFolders();
        this.getBossaDataFiles();
    }

    public void saveDataFilesInExplicitFolders() {
        this.setFilesTargetLocationInExplicitFolders();
        this.getBossaDataFiles();
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

    private void getBossaDataFiles() {
            Path currencyZipFilePath = this.download(currencyURL, currencyZipFolderPath);
            Path fundZipFilePath = this.download(fundURL, fundZipFolderPath);
            ZipUtil.saveZipFileContent(currencyZipFilePath, currencyUnzipTargetPath);
            ZipUtil.saveZipFileContent(fundZipFilePath, fundUnzipTargetPath);
    }

    private Path download(String sourceURL, String targetDirectory) {
        url = this.getURL(sourceURL);
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

    private URL getURL(String urlString) {
        try {
            url = new URL(urlString);
        } catch (IOException e) {
            LOGGER.error("Failed to get URL from String",e.getMessage());
        }
        return url;
    }
}


