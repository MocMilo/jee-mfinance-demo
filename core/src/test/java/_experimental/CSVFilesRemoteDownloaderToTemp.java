package _experimental;


import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CSVFilesRemoteDownloaderToTemp {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFilesRemoteDownloaderToTemp.class);
    private Configuration configuration;
    private final String DATE_PATTERN = "yyyyMMdd";
    private String currencyTargetPath;
    private String fundTargetPath;
    Path targetPath;

    public CSVFilesRemoteDownloaderToTemp(Configuration configuration) {
        this.configuration = configuration;
        this.currencyTargetPath = configuration.getCurrencyFolderPath().getFolderPath();
        this.fundTargetPath = configuration.getFundFolderPath().getFolderPath();
    }

    public void getModelFilesFromRemoteLocation() {

        Path currencyZipFilePath = this.downloadZipToTempFolder(configuration.getCurrencyUrl().getFileUrl(),
                configuration.getCurrencyBackupFolderPath().getFolderPath());

        Path fundZipFilePath = this.downloadZipToTempFolder(configuration.getFundUrl().getFileUrl(),
                configuration.getFundBackupFolderPath().getFolderPath());

        /*this.saveZipFileContent(currencyZipFilePath,  "/home/milo/zipfiles/");
        this.saveZipFileContent(fundZipFilePath, "/home/milo/zipfiles/");
*/
        this.saveZipFileContent(currencyZipFilePath, currencyTargetPath);
        this.saveZipFileContent(fundZipFilePath, fundTargetPath);


    }

    private Path downloadZipToTempFolder(String sourceURL, String targetDirectory) {
        try {
            URL url = new URL(sourceURL);
            String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());

            // targetDirectory = "/home/milo/zipfiles/";

            //File dir = new File(targetDirectory);

            //String path = dir.getAbsolutePath();

            //File.createTempFile("temp",fileName, dir);

            targetPath = new File(targetDirectory
                    .concat(fileName))
                    .toPath();

            InputStream stream = url.openStream();
            Files.copy(stream, targetPath, REPLACE_EXISTING);

            stream.close();

          //  LOGGER.info("File saved, source:{} target:{}", sourceURL, targetPath);

        } catch (IOException e) {
            LOGGER.error("Failed to update csv files, download source:{} target path:{}", sourceURL, targetPath);
        }

        System.out.println("targetDirectory:" + targetDirectory);
        File[] numberOfFiles = new File(targetDirectory).listFiles();

        System.out.println("Number of downloaded files in target folder:" + numberOfFiles.length);

        for (File item : numberOfFiles) {

           // System.out.println("item:" + item.getAbsolutePath());
            //System.out.println("item:" + item.getTotalSpace());
            // System.out.println("item:" + item.getFreeSpace());
        }

        return targetPath;
    }

    private void saveZipFileContent(Path zipFilePath, String targetExtractionPath) {

        final int BUFFER = 2048;

        try {
            BufferedOutputStream dest = null;

            FileInputStream fis = new FileInputStream(zipFilePath.toString());
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                int count;
                byte data[] = new byte[BUFFER];

                File tempEntryDir = new File(targetExtractionPath);
                File entryTempFile = File.createTempFile("tmp", entry.getName(), tempEntryDir);

                FileOutputStream fos = new FileOutputStream(entryTempFile);

               // LOGGER.info("Updating CSV locations:{} path:{}", entry, targetExtractionPath
               //         .concat(entry.getName()));

                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER))
                        != -1) {
                    dest.write(data, 0, count);
                }

                System.out.println("target unzip path:" + targetExtractionPath.toString());


                File[] numberOfFiles = new File(targetExtractionPath.toString()).listFiles();

                System.out.println("Number of unziped files:" + numberOfFiles.length);

                for (File item : numberOfFiles) {
                    // System.out.println("unziped item:" + item.getAbsolutePath());
                    // System.out.println("unziped item:" + item.getTotalSpace());
                    // System.out.println("unziped item:" + item.getFreeSpace());
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


