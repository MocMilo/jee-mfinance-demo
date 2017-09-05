package com.infoshare.core.appconfiguration;

import com.infoshare.core.appconfiguration.exception.AppConfigurationProviderException;
import com.infoshare.core.file.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.infoshare.core.file.path.*;
import com.infoshare.core.file.path.*;
import com.infoshare.core.file.url.CurrencyUrl;
import com.infoshare.core.file.url.FundUrl;
import com.infoshare.core.file.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfigurationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfigurationProvider.class);
    private final String CONFIGURATION_FILE_PATH = "Configuration.json";
    private final String OS_USER_NAME_FILE_VARIABLE = "user_name";
    private String osUserName;

    private FundFolderPath fundFolderPath;
    private CurrencyFolderPath currencyFolderPath;

    private FundBackupFolderPath fundBackupFolderPath;
    private CurrencyBackupFolderPath currencyBackupFolderPath;

    private FundUrl fundUrl;
    private CurrencyUrl currencyUrl;

    private String externalResourceFilePath;

    private List<FilePath> fundFilePaths = new ArrayList<>();
    private List<FilePath> currencyFilePaths = new ArrayList<>();


    public List<FilePath> getFundFilePaths() {
        return fundFilePaths;
    }

    public List<FilePath> getCurrencyFilePaths() {
        return currencyFilePaths;
    }

    private void setFundFolderPath(FundFolderPath fundFolderPath) {
        this.fundFolderPath = fundFolderPath;
    }

    private void setCurrencyFolderPath(CurrencyFolderPath currencyFolderPath) {
        this.currencyFolderPath = currencyFolderPath;
    }

    public String getExternalResourceFilePath() {
        return externalResourceFilePath;
    }

    public void setExternalResourceFilePath(String externalResourceFilePath) {
        this.externalResourceFilePath = externalResourceFilePath;
    }

    public FundUrl getFundUrl() {
        return fundUrl;
    }

    public CurrencyUrl getCurrencyUrl() {
        return currencyUrl;
    }

    public FundBackupFolderPath getFundBackupFolderPath() {
        return fundBackupFolderPath;
    }

    public CurrencyBackupFolderPath getCurrencyBackupFolderPath() {
        return currencyBackupFolderPath;
    }

    public FundFolderPath getFundFolderPath() {
        return fundFolderPath;
    }

    public CurrencyFolderPath getCurrencyFolderPath() {
        return currencyFolderPath;
    }

    public AppConfigurationProvider getConfiguration() {
        FileReader fileReader = new FileReader(CONFIGURATION_FILE_PATH);
        try {
            String fileContent = fileReader.getFileAsString();
            JSONMapper jsonMapper = new JSONMapper(fileContent);

            this.fundFilePaths = jsonMapper.getAppConfigurationFromJson().fundFilePaths;
            this.currencyFilePaths = jsonMapper.getAppConfigurationFromJson().currencyFilePaths;

            this.fundFolderPath = jsonMapper.getAppConfigurationFromJson().fundFolderPath;
            this.currencyFolderPath = jsonMapper.getAppConfigurationFromJson().currencyFolderPath;

            this.fundBackupFolderPath = jsonMapper.getAppConfigurationFromJson().fundBackupFolderPath;
            this.currencyBackupFolderPath = jsonMapper.getAppConfigurationFromJson().currencyBackupFolderPath;

            this.fundUrl = jsonMapper.getAppConfigurationFromJson().fundUrl;
            this.currencyUrl = jsonMapper.getAppConfigurationFromJson().currencyUrl;

            this.externalResourceFilePath = jsonMapper.getAppConfigurationFromJson().externalResourceFilePath;

            if (fundFilePaths.isEmpty() || fundFilePaths == null) {
                this.fundFilePaths = this.generateFilePaths(fundFolderPath.getFolderPath(),
                        this.getFileNameList(fundFolderPath.getFolderPath()));
            }

            if (currencyFilePaths.isEmpty() || currencyFilePaths == null) {
                this.currencyFilePaths = this.generateFilePaths(currencyFolderPath.getFolderPath(),
                        this.getFileNameList(currencyFolderPath.getFolderPath()));
            }


        } catch (IOException e) {
            LOGGER.info("Error reading the file: " + e.getMessage());
        } catch (AppConfigurationProviderException e) {
            LOGGER.info("Error creating the appconfiguration: " + e.getMessage());
        }
        return this;
    }


    private List<String> getFileNameList(String folderPath) {
        List<String> fileNames = new ArrayList<>();

        File[] files = new File(folderPath).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }

    private List<FilePath> generateFilePaths(String folderPath, List<String> fileList) {


        List<FilePath> filePaths = new ArrayList<>();
        for (String fileName : fileList) {
            filePaths.add(new FilePath(folderPath.concat(fileName)));
        }
        return filePaths;
    }


}
