package com.infoshareacademy.mfinance.core.builders;

import com.infoshareacademy.mfinance.core.models.locations.path.FilePath;
import com.infoshareacademy.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshareacademy.mfinance.core.models.bossa.InvestmentFund;
import com.infoshareacademy.mfinance.core.models.bossa.Investment;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.builders.investment.InvestmentCurrencyListBuilder;
import com.infoshareacademy.mfinance.core.builders.investment.InvestmentFundListBuilder;
import com.infoshareacademy.mfinance.core.providers.ConfigurationProvider;
import com.infoshareacademy.mfinance.core.providers.bossadata.BossaDataFilesProvider;
import com.infoshareacademy.mfinance.core.providers.bossadata.locations.FilePathsProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataContainerBuilder {
    /**
     * returns: DataContainer  - build from csv files
     * The most actual Bossa csv files are downloaded from external resource in zip format by URL.
     * Note:
     * DataContainerBuilder works in two modes: 'demo' and 'production'
     * DEMO MODE (simple deployment: application uses temporary folders)
     * set property to:
     * IS_DEMO_MODE = true
     * For production mode (requires deployment configuration: application uses folders
     * in explicit locations, defined in configuration.json)
     * set property to:
     * IS_DEMO_MODE = false
     */
    private static final boolean IS_DEMO_MODE = true;
    private static final String CONFIGURATION_FILE_PATH = "configuration/configuration.json";

    private Configuration configuration;
    private InvestmentFundListBuilder investmentFundBuilder = new InvestmentFundListBuilder();
    private InvestmentCurrencyListBuilder investmentCurrencyBuilder = new InvestmentCurrencyListBuilder();

    private List<InvestmentFund> investmentFunds = new ArrayList<>();
    private List<InvestmentCurrency> investmentCurrencies = new ArrayList<>();
    private List<Investment> investments = new ArrayList<>();

    private List<FilePath> currencyFilePaths = new ArrayList<>();
    private List<FilePath> fundFilePaths = new ArrayList<>();

    private FilePathsProvider filePathsProvider = new FilePathsProvider();

    public DataContainerBuilder() throws IOException {
        configuration = new ConfigurationProvider(CONFIGURATION_FILE_PATH)
                .getConfiguration();
    }

    public DataContainerBuilder(String resourcesFilePath) throws IOException {
        configuration = new ConfigurationProvider(resourcesFilePath)
                .getConfiguration();
    }

    public DataContainer getDataContainer() throws IOException {
        new BossaDataFilesProvider(configuration, IS_DEMO_MODE).getCSVFiles();
        if (IS_DEMO_MODE) {
            currencyFilePaths = filePathsProvider.generateTempCurrencyFilePaths();
            fundFilePaths = filePathsProvider.generateTempFundFilePaths();
        } else {
            currencyFilePaths = filePathsProvider.generateCurrencyFilePaths(configuration);
            fundFilePaths = filePathsProvider.generateFundFilePaths(configuration);
        }
        this.buildFunds();
        this.buildCurrencies();
        return new DataContainer(investmentFundBuilder.getNumberOfFunds(),
                investmentCurrencyBuilder.getNumberOfCurrencies(), investments);
    }

    private void buildCurrencies() throws IOException {
        for (FilePath filePath : currencyFilePaths) {
            investmentCurrencyBuilder.createCurrenciesFromFile(filePath.getFilePath());
        }
        investmentCurrencies = investmentCurrencyBuilder.getCurrencies();
        investments.addAll(investmentCurrencies);
    }

    private void buildFunds() throws IOException {
        for (FilePath filePath : fundFilePaths) {
            investmentFundBuilder.createFundsFromFile(filePath.getFilePath());
        }
        investmentFunds = investmentFundBuilder.getInvestmentFunds();
        investments.addAll(investmentFunds);
    }
}
