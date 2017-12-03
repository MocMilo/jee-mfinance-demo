package com.infoshareacademy.mfinance.core.utils;

import java.io.*;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {
    public static void saveZipFileContent(Path zipFilePath, String targetExtractionPath) throws IOException {
        final int BUFFER = 2048;

        try (FileInputStream fis = new FileInputStream(zipFilePath.toString());
             ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis))) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                try (FileOutputStream fos = new FileOutputStream(targetExtractionPath
                        .concat(entry.getName()))) {

                    int count;
                    byte data[] = new byte[BUFFER];

                    try (BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER)) {
                        while ((count = zis.read(data, 0, BUFFER)) != -1) {
                            dest.write(data, 0, count);
                        }
                    }
                }
            }
        }
    }
}
