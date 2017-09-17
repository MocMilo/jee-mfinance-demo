package com.infoshare.core.file;


import com.infoshare.core.configuration.ConfigurationProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class RemoteDownloader {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteDownloader.class);
    private ConfigurationProvider configurationProvider = new ConfigurationProvider().getConfiguration();
    private final String DATE_PATTERN = "yyyyMMdd";
    private final String currencyTargetPath = configurationProvider.getCurrencyFolderPath().getFolderPath();
    private final String fundTargetPath = configurationProvider.getFundFolderPath().getFolderPath();


    public void getModelFilesFromRemoteLocation() throws IOException {
        try {
            Path currencyZipFilePath = this.download(configurationProvider.getCurrencyUrl().getFileUrl(),
                    configurationProvider.getCurrencyBackupFolderPath().getFolderPath());

            Path fundZipFilePath = this.download(configurationProvider.getFundUrl().getFileUrl(),
                    configurationProvider.getFundBackupFolderPath().getFolderPath());

            this.saveZipFileContent(currencyZipFilePath, currencyTargetPath);
            this.saveZipFileContent(fundZipFilePath, fundTargetPath);

        } catch (IOException ex) {
            LOGGER.error("FAILED To download file from remote location: {}", ex.getMessage());
        }
    }

    private Path download(String sourceURL, String targetDirectory) throws IOException {
        URL url = new URL(sourceURL);
        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
        Path targetPath = new File(targetDirectory
                .concat(File.separator)
                .concat(this.getFileNameWithDate(fileName)))
                .toPath();

        Files.copy(url.openStream(), targetPath, REPLACE_EXISTING);

        LOGGER.info("File saved, source:{} target:{}", sourceURL, targetPath);
        return targetPath;
    }

    private String getFileNameWithDate(String defaultFileName) {

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String formattedDate = localDate.format(formatter);

        return formattedDate
                .concat("_")
                .concat(defaultFileName);
    }

    private void saveZipFileContent(Path zipFilePath, String targetExtractionPath) throws IOException {

        final int BUFFER = 2048;

        try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new FileInputStream(zipFilePath.toString());
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                int count;
                byte data[] = new byte[BUFFER];

                FileOutputStream fos = new FileOutputStream(targetExtractionPath
                        .concat(entry.getName()));

                LOGGER.info("Updating CSV file:{} path:{}", entry, targetExtractionPath
                        .concat(entry.getName()));

                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER))
                        != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
            }
            zis.close();
        } catch (Exception e) {
            LOGGER.error("Failed to save zip content:{}, {}, {}", zipFilePath, targetExtractionPath, e.getMessage());
            e.printStackTrace();
        }
    }
}


