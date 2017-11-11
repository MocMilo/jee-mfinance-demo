package com.infoshare.mfinance.core.providers.bossadata;


import com.infoshare.mfinance.core.models.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DemoFilesProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoFilesProvider.class);
    private Configuration configuration;

    public DemoFilesProvider(Configuration configuration) {
        this.configuration = configuration;
    }

    public void getCSVFiles() {

        boolean isFilesDownloadSucceed;
        boolean isResourceFilesFetchSucceed = false;

        isFilesDownloadSucceed = new RemoteDataFilesProvider(configuration)
                .saveFilesInTemp();

        if (!isFilesDownloadSucceed) {

            LOGGER.info("Reading files from application resources...");

            isResourceFilesFetchSucceed = new ResourcesDataFilesProvider()
                    .saveFilesInTemp();
        }

        if (!isFilesDownloadSucceed && !isResourceFilesFetchSucceed) {

            LOGGER.error("Failed to read csv files in Demo mode.");
            throw new IllegalStateException("Failed to read csv files in Demo mode.");
        }
    }

}
