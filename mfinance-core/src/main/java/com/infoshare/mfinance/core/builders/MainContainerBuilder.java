package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.configuration.ConfigurationProvider;
import com.infoshare.mfinance.core.file.path.FilePath;
import com.infoshare.mfinance.core.models.bossa.Currency;
import com.infoshare.mfinance.core.models.bossa.Fund;
import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.mfinance.core.models.bossa.MainContainer;
import com.infoshare.mfinance.core.models.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MainContainerBuilder {

    private Configuration configuration = new ConfigurationProvider().getConfiguration();
    private MainContainer mainContainer = new MainContainer();
    private FundBuilder fundBuilder = new FundBuilder();
    private CurrencyBuilder currencyBuilder = new CurrencyBuilder();

    private List<Fund> funds = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();
    private List<Investment> investments = new ArrayList<>();

    public MainContainer getMainContainer() {

        this.buildFunds();
        this.buildCurrencies();

        mainContainer.setInvestments(investments);
        mainContainer.setFundsCount(fundBuilder.getNumberOfFunds());
        mainContainer.setCurrenciesCount(currencyBuilder.getNumberOfCurrencies());
        return mainContainer;
    }

    private void buildFunds() {
        configuration.getFundFilePaths().forEach((FilePath filePath) -> {
            fundBuilder.createFundsFromFile(filePath.getFilePath());
        });
        funds = fundBuilder.getFunds();
        investments.addAll(funds);
    }

    private void buildCurrencies() {
        configuration.getCurrencyFilePaths()
                .forEach((FilePath filePath) -> {
                    currencyBuilder.createCurrenciesFromFile(filePath.getFilePath());
                });
        currencies = currencyBuilder.getCurrencies();
        investments.addAll(currencies);
    }
}
