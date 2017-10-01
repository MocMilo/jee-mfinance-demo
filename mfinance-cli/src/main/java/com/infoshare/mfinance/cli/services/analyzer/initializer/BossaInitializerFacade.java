package com.infoshare.mfinance.cli.services.analyzer.initializer;

import com.infoshare.core.configuration.ConfigurationProvider;
import com.infoshare.core.loader.MainContainerLoader;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.configuration.Configuration;

public class BossaInitializerFacade {

    public MainContainer getMainContainerWithLoadedData(){
        Configuration configuration = new ConfigurationProvider().getConfiguration();
        MainContainerLoader mainContainerLoader = new MainContainerLoader(configuration);

        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();

        return mainContainerLoader.getMainContainer();
    }
}
