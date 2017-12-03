package com.infoshareacademy.web.services.bossa;

import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.builders.DataContainerBuilder;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class DataContainerImpl implements DataContainerService {
    private DataContainer dataContainer;

    @PostConstruct
    public void onPostConstruct() {
        dataContainer = new DataContainerBuilder().getDataContainer();
    }

    @Override
    public DataContainer getDataContainer() {
        return dataContainer;
    }

    @Override
    public synchronized void reload() {
        dataContainer = new DataContainerBuilder().getDataContainer();
    }
}
