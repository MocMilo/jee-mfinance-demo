package com.infoshare.web.container;

import com.infoshare.mfinance.core.builders.DataContainerBuilder;
import com.infoshare.mfinance.core.models.bossa.DataContainer;
import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.web.adminpanel.trigger.ITriggerable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ModelContainer implements IModelContainerService, ITriggerable {

    private List<Investment> investments;
    private DataContainer dataContainer;
    //private RemoteDataFilesProvider remoteDownloader = new RemoteDataFilesProvider();
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelContainer.class);

    public ModelContainer() {
        this.investments = new ArrayList<>();
    }

    @PostConstruct
    public void onPostConstruct() {
       dataContainer = new DataContainerBuilder().getDataContainer();
    }

    @Override
    public void executeAction() {
/*        try {
           // this.updateModelFileResources();
            // this.initialize();
        } catch (Exception e) {
            LOGGER.error("Failed to execute action realoading models: {}", e.getMessage());
        }*/
    }

    @Override
    public void reload() {
        this.initialize();
    }

    private void initialize() {

        //this.updateModelFileResources();

  /*      if (!this.dataContainer.getInvestments().isEmpty()) {
            this.dataContainer.getInvestments().clear();
        }*/

    }

    public void updateModelFileResources() {
/*        try {
            LOGGER.info("Data models CSV Zip files download initialized...");
            remoteDownloader.getBossaModelFiles();
        } catch (IOException e) {
            LOGGER.error("Failed to download CSV Zip files from remote location. Model cannot be actualized! {}", e.getMessage());
        }*/
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public DataContainer getDataContainer() {
        return dataContainer;
    }

}
