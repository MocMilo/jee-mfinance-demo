package com.infoshareacademy.mfinance.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnzipUtil.class);
    private static UnzipUtil instance;

    static {
        try {
            instance = new UnzipUtil();
        } catch (Exception e) {
            throw new RuntimeException("Exception was thrown when creating UnzipUtil singleton instance.");
        }
    }

    private static UnzipUtil getInstance() {
        return instance;
    }

    private UnzipUtil() {
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

        } catch (Exception e) {
            LOGGER.error("Failed to unzip:{}, target unzip path:{}, message:{}", zipFilePath, targetExtractionPath, e.getMessage());
            e.printStackTrace();
        }
    }
}
