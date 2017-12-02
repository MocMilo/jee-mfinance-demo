package com.infoshareacademy.mfinance.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtil.class);
    private static ZipUtil instance;

    static {
        try {
            instance = new ZipUtil();
        } catch (Exception e) {
            throw new RuntimeException("Exception was thrown when creating ZipUtil singleton instance.");
        }
    }

    private static ZipUtil getInstance() {
        return instance;
    }

    private ZipUtil() {
    }

    public static void saveZipFileContent(Path zipFilePath, String targetExtractionPath) {
        final int BUFFER = 2048;
        int zipEntryCounter = 0;

        try (FileInputStream fis = new FileInputStream(zipFilePath.toString());
             ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis))) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                try (FileOutputStream fos =
                             new FileOutputStream(targetExtractionPath.concat(entry.getName()))) {

                    int count;
                    byte data[] = new byte[BUFFER];
                    LOGGER.trace("Unzip file to location:{}", targetExtractionPath
                            .concat(entry.getName()));
                    try (BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER)) {
                        while ((count = zis.read(data, 0, BUFFER)) != -1) {
                            dest.write(data, 0, count);
                        }
                        zipEntryCounter++;
                    }
                }
            }
            LOGGER.info("Target unzip path:{}", targetExtractionPath);
            LOGGER.info("Number of extracted files:{}", zipEntryCounter);
        } catch (IOException e) {
            LOGGER.error("Failed to unzip:{}, target unzip path:{}, message:{}", zipFilePath, targetExtractionPath, e.getMessage());
        }
    }
}
