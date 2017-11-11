package com.infoshare.mfinance.core.providers.bossadata;


import com.infoshare.mfinance.core.models.configuration.Configuration;
import com.infoshare.mfinance.core.utils.TemporaryFoldersProviderUtil;
import com.infoshare.mfinance.core.utils.UnzipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class RemoteDataFilesProvider implements IBossaDataProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteDataFilesProvider.class);
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


    public RemoteDataFilesProvider(Configuration configuration) {

        this.configuration = configuration;

        currencyURL = configuration.getCurrencyUrl().getFileUrl();
        fundURL = configuration.getFundUrl().getFileUrl();

    }

    public boolean saveFilesInTemp() {

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

        return getBossaModelFiles();
    }

    public boolean saveFilesInExplicitFolders() {

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

        return getBossaModelFiles();
    }

    private boolean getBossaModelFiles() {
        try {

            Path currencyZipFilePath = this.download(currencyURL, currencyZipFolderPath);
            Path fundZipFilePath = this.download(fundURL, fundZipFolderPath);

            UnzipUtil.saveZipFileContent(currencyZipFilePath, currencyUnzipTargetPath);
            UnzipUtil.saveZipFileContent(fundZipFilePath, fundUnzipTargetPath);
            return true;

        } catch (IOException e) {
            LOGGER.error("Failed to update Bossa csv files from remote location (resource unavailable).");
            return false;
        }
    }

    private Path download(String sourceURL, String targetDirectory) throws IOException {

        URL url = new URL(sourceURL);
        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
        targetPath = new File(targetDirectory
                .concat(File.separator)
                .concat(this.getFileNameWithDate(fileName)))
                .toPath();

        InputStream stream = url.openStream();
        Files.copy(stream, targetPath, REPLACE_EXISTING);

        stream.close();

        LOGGER.info("File saved: source:{}, target location:{}", sourceURL, targetPath);

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


