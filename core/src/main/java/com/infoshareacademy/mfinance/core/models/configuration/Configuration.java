package com.infoshareacademy.mfinance.core.models.configuration;

import com.infoshareacademy.mfinance.core.models.locations.url.CurrencyUrl;
import com.infoshareacademy.mfinance.core.models.locations.url.FundUrl;
import com.infoshareacademy.mfinance.core.models.locations.path.CurrencyBackupFolderPath;
import com.infoshareacademy.mfinance.core.models.locations.path.CurrencyFolderPath;
import com.infoshareacademy.mfinance.core.models.locations.path.FundBackupFolderPath;
import com.infoshareacademy.mfinance.core.models.locations.path.FundFolderPath;

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
