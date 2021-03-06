package com.infoshareacademy.mfinance.core.utils;

import com.google.common.io.Files;
import com.infoshareacademy.mfinance.core.models.locations.path.CurrencyBackupFolderPath;
import com.infoshareacademy.mfinance.core.models.locations.path.CurrencyFolderPath;
import com.infoshareacademy.mfinance.core.models.locations.path.FundBackupFolderPath;
import com.infoshareacademy.mfinance.core.models.locations.path.FundFolderPath;

public final class TemporaryFoldersProviderUtil {
    private static TemporaryFoldersProviderUtil instance;
    private static CurrencyFolderPath currencyFolderPath = new CurrencyFolderPath();
    private static FundFolderPath fundFolderPath = new FundFolderPath();
    private static CurrencyBackupFolderPath currencyBackupFolderPath = new CurrencyBackupFolderPath();
    private static FundBackupFolderPath fundBackupFolderPath = new FundBackupFolderPath();

    public static CurrencyFolderPath getCurrencyFolderPath() {
        return currencyFolderPath;
    }

    public static FundFolderPath getFundFolderPath() {
        return fundFolderPath;
    }

    public static CurrencyBackupFolderPath getCurrencyBackupFolderPath() {
        return currencyBackupFolderPath;
    }

    public static FundBackupFolderPath getFundBackupFolderPath() {
        return fundBackupFolderPath;
    }

    static {
        try {
            instance = new TemporaryFoldersProviderUtil();
        } catch (Exception e) {
            throw new RuntimeException("Exception was thrown when creating TemporaryFoldersProviderUtil singleton instance.");
        }
    }

    private TemporaryFoldersProviderUtil() {
        String currencyFolder = Files.createTempDir()
                .getAbsolutePath()
                .concat("/");

        String fundFolder = Files.createTempDir()
                .getAbsolutePath()
                .concat("/");

        String currencyZipFolder = Files.createTempDir()
                .getAbsolutePath()
                .concat("/");

        String fundZipFolder = Files.createTempDir()
                .getAbsolutePath()
                .concat("/");

        currencyFolderPath.setFolderPath(currencyFolder);
        fundFolderPath.setFolderPath(fundFolder);
        currencyBackupFolderPath.setFolderPath(currencyZipFolder);
        fundBackupFolderPath.setFolderPath(fundZipFolder);
    }

    private static TemporaryFoldersProviderUtil getInstance() {
        return instance;
    }
}
