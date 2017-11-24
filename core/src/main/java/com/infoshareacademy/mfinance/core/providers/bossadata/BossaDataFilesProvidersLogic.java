package com.infoshareacademy.mfinance.core.providers.bossadata;


import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BossaDataFilesProvidersLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(BossaDataFilesProvidersLogic.class);
    private Configuration configuration;
    private boolean tempTargetFolders;

    public BossaDataFilesProvidersLogic(Configuration configuration, boolean tempTargetFolders) {
        this.configuration = configuration;
        this.tempTargetFolders=tempTargetFolders;
    }

    public void getFilesInFailSafeMode() {

        boolean isFilesDownloadSucceed;
        boolean isResourceFilesFetchSucceed = false;

        if(tempTargetFolders) {
            isFilesDownloadSucceed = new RemoteLocationDataFilesProvider(configuration)
                    .saveDataFilesInTempFolders();
        }else {
            isFilesDownloadSucceed = new RemoteLocationDataFilesProvider(configuration)
                    .saveDataFilesInExplicitFolders();
        }

        if (!isFilesDownloadSucceed) {

            LOGGER.info("Reading files from application resources...");

            if(tempTargetFolders) {
                isResourceFilesFetchSucceed = new ResourcesLocationDataFilesProvider(configuration)
                        .saveDataFilesInTempFolders();
            } else {
                isResourceFilesFetchSucceed = new ResourcesLocationDataFilesProvider(configuration)
                        .saveDataFilesInExplicitFolders();
            }

        }

        if (!isFilesDownloadSucceed && !isResourceFilesFetchSucceed) {

            LOGGER.error("Failed to read csv files in fail-safe mode.");
            throw new IllegalStateException("Failed to read csv files in fail-safe mode.");
        }
    }
}
