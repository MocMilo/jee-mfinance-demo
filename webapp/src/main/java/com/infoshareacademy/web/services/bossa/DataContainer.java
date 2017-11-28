package com.infoshareacademy.web.services.bossa;

import com.infoshareacademy.mfinance.core.builders.DataContainerBuilder;
import com.infoshareacademy.mfinance.core.models.bossa.Investment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DataContainer implements IDataContainerService {


    private com.infoshareacademy.mfinance.core.models.bossa.DataContainer dataContainer;




    @PostConstruct
    public void onPostConstruct() {
       dataContainer = new DataContainerBuilder().getDataContainer();
    }

    @Override

    public void  reload() {
    dataContainer = new DataContainerBuilder().getDataContainer();
    }
    // public void synchronized reload() { dataContainer = new DataContainerBuilder().getDataContainer();

    public com.infoshareacademy.mfinance.core.models.bossa.DataContainer getDataContainer() {
        return dataContainer;
    }

}
