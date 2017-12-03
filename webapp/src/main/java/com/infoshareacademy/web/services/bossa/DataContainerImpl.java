package com.infoshareacademy.web.services.bossa;

import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.builders.DataContainerBuilder;
import com.infoshareacademy.web.services.analyzer.IVRAnalysisStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class DataContainerImpl implements DataContainerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IVRAnalysisStrategy.class);
    private DataContainer dataContainer;

    @PostConstruct
    public void onPostConstruct() {
        try {
            dataContainer = new DataContainerBuilder().getDataContainer();
        } catch (IOException e) {
            LOGGER.error("Failed to build DataContainer:{}", e.getMessage());
        }
    }

    @Override
    public DataContainer getDataContainer() {
        return dataContainer;
    }

    @Override
    public synchronized void reload() {
        try {
            dataContainer = new DataContainerBuilder().getDataContainer();
        } catch (IOException e) {
            LOGGER.error("Failed to reload DataContainer:{}", e.getMessage());
        }
    }
}
