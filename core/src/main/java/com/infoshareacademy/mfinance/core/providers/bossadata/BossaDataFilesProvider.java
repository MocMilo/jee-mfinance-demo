package com.infoshareacademy.mfinance.core.providers.bossadata;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import java.io.IOException;

public class BossaDataFilesProvider {
    private Configuration configuration;
    private boolean tempTargetFolders;

    public BossaDataFilesProvider(Configuration configuration, boolean tempTargetFolders) {
        this.configuration = configuration;
        this.tempTargetFolders = tempTargetFolders;
    }

    public void getCSVFiles() throws IOException{
        if (tempTargetFolders) {
            new RemoteLocationDataFilesProvider(configuration)
                    .saveDataFilesInTempFolders();
        } else {
            new RemoteLocationDataFilesProvider(configuration)
                    .saveDataFilesInExplicitFolders();
        }
    }
}
