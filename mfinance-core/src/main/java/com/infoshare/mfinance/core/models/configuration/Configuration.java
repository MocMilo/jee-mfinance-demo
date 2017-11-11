package com.infoshare.mfinance.core.models.configuration;

import com.infoshare.mfinance.core.models.locations.path.*;
import com.infoshare.mfinance.core.models.locations.url.CurrencyUrl;
import com.infoshare.mfinance.core.models.locations.url.FundUrl;

public class Configuration {

    private FundFolderPath fundFolderPath;
    private CurrencyFolderPath currencyFolderPath;

    private FundBackupFolderPath fundBackupFolderPath;
    private CurrencyBackupFolderPath currencyBackupFolderPath;

    private FundUrl fundUrl;
    private CurrencyUrl currencyUrl;


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

}
