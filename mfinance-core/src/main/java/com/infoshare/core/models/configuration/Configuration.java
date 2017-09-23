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

    private List<FilePath> fundFilePaths = new ArrayList<>();
    private List<FilePath> currencyFilePaths = new ArrayList<>();

    public FundFolderPath getFundFolderPath() {
        return fundFolderPath;
    }

    public CurrencyFolderPath getCurrencyFolderPath() {
        return currencyFolderPath;
    }

    public FundBackupFolderPath getFundBackupFolderPath() {
        return fundBackupFolderPath;
    }

    public CurrencyBackupFolderPath getCurrencyBackupFolderPath() {
        return currencyBackupFolderPath;
    }

    public FundUrl getFundUrl() {
        return fundUrl;
    }

    public CurrencyUrl getCurrencyUrl() {
        return currencyUrl;
    }

    public List<FilePath> getFundFilePaths() {
        return fundFilePaths;
    }

    public List<FilePath> getCurrencyFilePaths() {
        return currencyFilePaths;
    }

    public void setFundFilePaths(List<FilePath> fundFilePaths) {
        this.fundFilePaths = fundFilePaths;
    }

    public void setCurrencyFilePaths(List<FilePath> currencyFilePaths) {
        this.currencyFilePaths = currencyFilePaths;
    }
}
