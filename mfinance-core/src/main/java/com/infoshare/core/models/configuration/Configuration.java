package com.infoshare.core.models.configuration;

import com.infoshare.core.file.path.*;
import com.infoshare.core.file.url.CurrencyUrl;
import com.infoshare.core.file.url.FundUrl;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private FundFolderPath fundFolderPath;
    private CurrencyFolderPath currencyFolderPath;

    private FundBackupFolderPath fundBackupFolderPath;
    private CurrencyBackupFolderPath currencyBackupFolderPath;

    private FundUrl fundUrl;
    private CurrencyUrl currencyUrl;

    private String externalResourceFilePath;

    private List<FilePath> fundFilePaths = new ArrayList<>();
    private List<FilePath> currencyFilePaths = new ArrayList<>();

    public FundFolderPath getFundFolderPath() {
        return fundFolderPath;
    }

    public void setFundFolderPath(FundFolderPath fundFolderPath) {
        this.fundFolderPath = fundFolderPath;
    }

    public CurrencyFolderPath getCurrencyFolderPath() {
        return currencyFolderPath;
    }

    public void setCurrencyFolderPath(CurrencyFolderPath currencyFolderPath) {
        this.currencyFolderPath = currencyFolderPath;
    }

    public FundBackupFolderPath getFundBackupFolderPath() {
        return fundBackupFolderPath;
    }

    public void setFundBackupFolderPath(FundBackupFolderPath fundBackupFolderPath) {
        this.fundBackupFolderPath = fundBackupFolderPath;
    }

    public CurrencyBackupFolderPath getCurrencyBackupFolderPath() {
        return currencyBackupFolderPath;
    }

    public void setCurrencyBackupFolderPath(CurrencyBackupFolderPath currencyBackupFolderPath) {
        this.currencyBackupFolderPath = currencyBackupFolderPath;
    }

    public FundUrl getFundUrl() {
        return fundUrl;
    }

    public void setFundUrl(FundUrl fundUrl) {
        this.fundUrl = fundUrl;
    }

    public CurrencyUrl getCurrencyUrl() {
        return currencyUrl;
    }

    public void setCurrencyUrl(CurrencyUrl currencyUrl) {
        this.currencyUrl = currencyUrl;
    }

    public String getExternalResourceFilePath() {
        return externalResourceFilePath;
    }

    public void setExternalResourceFilePath(String externalResourceFilePath) {
        this.externalResourceFilePath = externalResourceFilePath;
    }

    public List<FilePath> getFundFilePaths() {
        return fundFilePaths;
    }

    public void setFundFilePaths(List<FilePath> fundFilePaths) {
        this.fundFilePaths = fundFilePaths;
    }

    public List<FilePath> getCurrencyFilePaths() {
        return currencyFilePaths;
    }

    public void setCurrencyFilePaths(List<FilePath> currencyFilePaths) {
        this.currencyFilePaths = currencyFilePaths;
    }
}
