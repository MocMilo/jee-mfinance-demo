package com.infoshare.controller.analyzer;

import com.infoshare.core.configuration.ConfigurationProvider;
import com.infoshare.core.loader.MainContainerLoader;
import com.infoshare.core.models.bossa.Investment;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.configuration.Configuration;

import java.util.List;

public class BossaInitializerFasade {

    public MainContainer getMainContainerWithLoadedData(){
        Configuration configuration = new ConfigurationProvider().getConfiguration();
        MainContainerLoader mainContainerLoader = new MainContainerLoader(configuration);

        // loading data
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();

        return mainContainerLoader.getMainContainer();
    }

}
