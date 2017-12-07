package com.infoshareacademy.mfinance.core.providers.bossadata;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;

import java.io.IOException;

public class BossaDataFilesProvider {

    public static void getCSVFiles(Configuration configuration, boolean isDemoMode) throws IOException {
        if (isDemoMode) {
            new RemoteLocationDataFilesProvider(configuration)
                    .saveDataFilesInTempFolders();
        } else {
            new RemoteLocationDataFilesProvider(configuration)
                    .saveDataFilesInExplicitFolders();
        }
    }
}
