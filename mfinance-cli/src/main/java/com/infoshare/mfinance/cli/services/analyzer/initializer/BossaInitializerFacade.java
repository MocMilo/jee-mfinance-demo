package com.infoshare.mfinance.cli.services.analyzer.initializer;

import com.infoshare.core.configuration.ConfigurationProvider;
import com.infoshare.core.builders.MainContainerBuilder;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.configuration.Configuration;

public class BossaInitializerFacade {

    public MainContainer getMainContainerWithLoadedData(){
        Configuration configuration = new ConfigurationProvider().getConfiguration();
        MainContainerBuilder mainContainerBuilder = new MainContainerBuilder(configuration);

        mainContainerBuilder.loadFunds();
        mainContainerBuilder.loadCurrencies();

        return mainContainerBuilder.getMainContainer();
    }
}
