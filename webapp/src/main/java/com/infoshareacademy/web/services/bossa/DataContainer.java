package com.infoshareacademy.web.services.bossa;

import com.infoshareacademy.mfinance.core.builders.DataContainerBuilder;
import com.infoshareacademy.mfinance.core.models.bossa.Investment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DataContainer implements IDataContainerService {

    private List<Investment> investments;
    private com.infoshareacademy.mfinance.core.models.bossa.DataContainer dataContainer;
    private static final Logger LOGGER = LoggerFactory.getLogger(DataContainer.class);

    public DataContainer() {
        this.investments = new ArrayList<>();
    }

    @PostConstruct
    public void onPostConstruct() {
       dataContainer = new DataContainerBuilder().getDataContainer();
    }

    @Override
    public void reload() {
    dataContainer = new DataContainerBuilder().getDataContainer();
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public com.infoshareacademy.mfinance.core.models.bossa.DataContainer getDataContainer() {
        return dataContainer;
    }

}
