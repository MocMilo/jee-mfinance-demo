package com.infoshare.web.container;

import com.infoshare.web.adminpanel.trigger.ITriggerable;
import com.infoshare.core.configuration.ConfigurationProvider;
import com.infoshare.core.file.RemoteDownloader;
import com.infoshare.core.builders.MainContainerBuilder;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.bossa.Investment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;

@Singleton
public class ModelContainer implements IModelContainerService, ITriggerable {

    private List<Investment> investments;
    private MainContainer mainContainer;
    private RemoteDownloader remoteDownloader = new RemoteDownloader();
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelContainer.class);

    @PostConstruct
    private void onPostConstruct() {

        //this.updateModelFileResources();

        ConfigurationProvider appCon = new ConfigurationProvider().getConfiguration();
        MainContainerBuilder mainContainerBuilder = new MainContainerBuilder(appCon);
        mainContainerBuilder.loadFunds();
        mainContainerBuilder.loadCurrencies();

        MainContainer mainContainer = mainContainerBuilder.getMainContainer();
        this.mainContainer = mainContainer;
        this.investments = mainContainer.getInvestments();

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

        if (!mainContainer.getInvestments().isEmpty()) {
            mainContainer.getInvestments().clear();
        }

        ConfigurationProvider appCon = new ConfigurationProvider().getConfiguration();
        MainContainerBuilder mainContainerBuilder = new MainContainerBuilder(appCon);
        mainContainerBuilder.loadFunds();
        mainContainerBuilder.loadCurrencies();

        MainContainer mainContainer = mainContainerBuilder.getMainContainer();
        this.investments = mainContainer.getInvestments();
        this.mainContainer = mainContainer;
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

    public MainContainer getMainContainer() {
        return mainContainer;
    }

}
