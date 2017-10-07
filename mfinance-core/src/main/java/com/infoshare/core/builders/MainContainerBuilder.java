package com.infoshare.core.builders;

import com.infoshare.core.file.path.FilePath;
import com.infoshare.core.models.bossa.Currency;
import com.infoshare.core.models.bossa.Fund;
import com.infoshare.core.models.bossa.Investment;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MainContainerBuilder {

    private Configuration configuration;
    private List<Fund> funds = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();
    private List<Investment> investments = new ArrayList<>();
    private MainContainer mainContainer = new MainContainer();
    private FundBuilder fundBuilder = new FundBuilder();
    private CurrencyBuilder currencyBuilder = new CurrencyBuilder();

    public void loadFunds() {
        configuration.getFundFilePaths().forEach((FilePath filePath) -> {
            fundBuilder.createFundsFromFile(filePath.getFilePath());
        });
        funds = fundBuilder.getFunds();
        investments.addAll(funds);
    }

    public void loadCurrencies() {
        configuration.getCurrencyFilePaths()
                .forEach((FilePath filePath) -> {
                    currencyBuilder.createCurrenciesFromFile(filePath.getFilePath());
                });
        currencies = currencyBuilder.getCurrencies();
        investments.addAll(currencies);
    }

    public MainContainerBuilder(Configuration appCon) {
        this.configuration = appCon;
    }

    public MainContainer getMainContainer() {
        mainContainer.setInvestments(investments);
        mainContainer.setFundsCount(fundBuilder.getNumberOfFunds());
        mainContainer.setCurrenciesCount(currencyBuilder.getNumberOfCurrencies());
        return mainContainer;
    }
}
