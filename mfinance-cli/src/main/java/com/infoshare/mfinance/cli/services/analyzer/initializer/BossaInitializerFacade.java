package com.infoshare.mfinance.cli.services.analyzer.initializer;

import com.infoshare.mfinance.core.configuration.ConfigurationProvider;
import com.infoshare.mfinance.core.builders.MainContainerBuilder;
import com.infoshare.mfinance.core.models.bossa.MainContainer;
import com.infoshare.mfinance.core.models.configuration.Configuration;

public class BossaInitializerFacade {

    public MainContainer getMainContainerWithLoadedData(){
        Configuration configuration = new ConfigurationProvider().getConfiguration();
        MainContainerBuilder mainContainerBuilder = new MainContainerBuilder(configuration);

        mainContainerBuilder.loadFunds();
        mainContainerBuilder.loadCurrencies();

        return mainContainerBuilder.getMainContainer();
    }
}
