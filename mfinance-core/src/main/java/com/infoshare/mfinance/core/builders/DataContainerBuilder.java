package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.configuration.ConfigurationProvider;
import com.infoshare.mfinance.core.file.path.FilePath;
import com.infoshare.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshare.mfinance.core.models.bossa.InvestmentFund;
import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.mfinance.core.models.bossa.DataContainer;
import com.infoshare.mfinance.core.models.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class DataContainerBuilder {

    private Configuration configuration = new ConfigurationProvider().getConfiguration();
    private DataContainer dataContainer = new DataContainer();
    private FundBuilder fundBuilder = new FundBuilder();
    private CurrencyBuilder currencyBuilder = new CurrencyBuilder();

    private List<InvestmentFund> investmentFunds = new ArrayList<>();
    private List<InvestmentCurrency> currencies = new ArrayList<>();
    private List<Investment> investments = new ArrayList<>();

    public DataContainer getDataContainer() {

        this.buildFunds();
        this.buildCurrencies();

        dataContainer.setInvestments(investments);
        dataContainer.setFundsCount(fundBuilder.getNumberOfFunds());
        dataContainer.setCurrenciesCount(currencyBuilder.getNumberOfCurrencies());
        return dataContainer;
    }

    private void buildFunds() {
        configuration.getFundFilePaths().forEach((FilePath filePath) -> {
            fundBuilder.createFundsFromFile(filePath.getFilePath());
        });
        investmentFunds = fundBuilder.getInvestmentFunds();
        investments.addAll(investmentFunds);
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
