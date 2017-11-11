package com.infoshare.mfinance.core.utils;

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

    public static UnzipUtil getInstance() {

        return instance;
    }

    private UnzipUtil(){

    }

    public static void saveZipFileContent(Path zipFilePath, String targetExtractionPath) {

        final int BUFFER = 2048;

        try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new FileInputStream(zipFilePath.toString());
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            int zipEntryCounter = 0;

            while ((entry = zis.getNextEntry()) != null) {

                int count;
                byte data[] = new byte[BUFFER];

                FileOutputStream fos = new FileOutputStream(targetExtractionPath
                        .concat(entry.getName()));

                LOGGER.trace("Unzip file to location:{}", targetExtractionPath
                        .concat(entry.getName()));

                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER))
                        != -1) {
                    dest.write(data, 0, count);
                }

                zipEntryCounter++;

                dest.flush();
                dest.close();
            }

            zis.close();

            LOGGER.info("Target unzip path:{}", targetExtractionPath);
            LOGGER.info("Number of extracted files:{}", zipEntryCounter);

        } catch (Exception e) {
            LOGGER.error("Failed to unzip:{}, target unzip path:{}, message:{}", zipFilePath, targetExtractionPath, e.getMessage());
            e.printStackTrace();
        }

    }

}
