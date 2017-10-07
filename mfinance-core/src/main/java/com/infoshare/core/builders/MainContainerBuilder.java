package com.infoshare.core.loader;

import com.infoshare.core.configuration.ConfigurationProvider;
import com.infoshare.core.file.path.FilePath;
import com.infoshare.core.models.bossa.Currency;
import com.infoshare.core.models.bossa.Fund;
import com.infoshare.core.models.bossa.Investment;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MainContainerLoader {

    private Configuration configuration;
    private List<Fund> funds = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();
    private List<Investment> investments = new ArrayList<>();
    private MainContainer mainContainer = new MainContainer();
    private FundLoader fundLoader = new FundLoader();
    private CurrencyLoader currencyLoader = new CurrencyLoader();

    public void loadFunds() {
        configuration.getFundFilePaths().forEach((FilePath filePath) -> {
            fundLoader.createFundsFromFile(filePath.getFilePath());
        });
        funds = fundLoader.getFunds();
        investments.addAll(funds);
    }

    public void loadCurrencies() {
        configuration.getCurrencyFilePaths()
                .forEach((FilePath filePath) -> {
                    currencyLoader.createCurrenciesFromFile(filePath.getFilePath());
                });
        currencies = currencyLoader.getCurrencies();
        investments.addAll(currencies);
    }

    public MainContainerLoader(Configuration appCon) {
        this.configuration = appCon;
    }

    public MainContainer getMainContainer() {
        mainContainer.setInvestments(investments);
        mainContainer.setFundsCount(fundLoader.getNumberOfFunds());
        mainContainer.setCurrenciesCount(currencyLoader.getNumberOfCurrencies());
        return mainContainer;
    }
}
