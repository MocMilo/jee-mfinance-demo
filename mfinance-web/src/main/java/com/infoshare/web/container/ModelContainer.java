package com.infoshare.web.container;

import com.infoshare.mfinance.core.models.bossa.DataContainer;
import com.infoshare.web.adminpanel.trigger.ITriggerable;
import com.infoshare.mfinance.core.configuration.ConfigurationProvider;
import com.infoshare.mfinance.core.file.RemoteDownloader;
import com.infoshare.mfinance.core.builders.DataContainerBuilder;
import com.infoshare.mfinance.core.models.bossa.Investment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;

@Singleton
public class ModelContainer implements IModelContainerService, ITriggerable {

    private List<Investment> investments;
    private DataContainer dataContainer;
    private RemoteDownloader remoteDownloader = new RemoteDownloader();
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelContainer.class);

    @PostConstruct
    private void onPostConstruct() {

        //this.updateModelFileResources();

        ConfigurationProvider appCon = new ConfigurationProvider().getConfiguration();
        DataContainerBuilder dataContainerBuilder = new DataContainerBuilder(appCon);
        dataContainerBuilder.loadFunds();
        dataContainerBuilder.loadCurrencies();

        DataContainer dataContainer = dataContainerBuilder.getDataContainer();
        this.dataContainer = dataContainer;
        this.investments = dataContainer.getInvestments();

    }

    @Override
    public void executeAction() {
        try {
            this.updateModelFileResources();
            // this.initialize();
        } catch (Exception e) {
            LOGGER.error("Failed to execute action realoading models: {}", e.getMessage());
        }
    }

    @Override
    public void reload() {
        this.initialize();
    }

    private void initialize() {

        this.updateModelFileResources();

        if (!this.dataContainer.getInvestments().isEmpty()) {
            this.dataContainer.getInvestments().clear();
        }

        ConfigurationProvider appCon = new ConfigurationProvider().getConfiguration();
        DataContainerBuilder dataContainerBuilder = new DataContainerBuilder(appCon);
        dataContainerBuilder.loadFunds();
        dataContainerBuilder.loadCurrencies();

        DataContainer dataContainer = dataContainerBuilder.getDataContainer();
        this.investments = dataContainer.getInvestments();
        this.dataContainer = dataContainer;
    }

    public void updateModelFileResources() {
        try {
            LOGGER.info("Data models CSV Zip files download initialized...");
            remoteDownloader.getModelFilesFromRemoteLocation();
        } catch (IOException e) {
            LOGGER.error("Failed to download CSV Zip files from remote location. Model cannot be actualized! {}", e.getMessage());
        }
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public DataContainer getDataContainer() {
        return dataContainer;
    }

}
