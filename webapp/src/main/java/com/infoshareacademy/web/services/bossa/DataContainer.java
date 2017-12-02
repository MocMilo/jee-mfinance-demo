package com.infoshareacademy.web.services.bossa;

import com.infoshareacademy.mfinance.core.builders.DataContainerBuilder;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class DataContainer implements IDataContainerService {
    private com.infoshareacademy.mfinance.core.models.bossa.DataContainer dataContainer;

    @PostConstruct
    public void onPostConstruct() {
        dataContainer = new DataContainerBuilder().getDataContainer();
    }

    @Override

    public synchronized void reload() {
        dataContainer = new DataContainerBuilder().getDataContainer();
    }

    public com.infoshareacademy.mfinance.core.models.bossa.DataContainer getDataContainer() {
        return dataContainer;
    }
}
