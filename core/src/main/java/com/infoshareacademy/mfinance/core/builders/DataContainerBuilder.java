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
import com.infoshareacademy.mfinance.core.providers.bossadata.BossaDataFilesProvidersLogic;
import com.infoshareacademy.mfinance.core.providers.bossadata.locations.FilePathsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DataContainerBuilder {

    /**
     * @return DataContainer  - build from csv files
     * <p>
     * Note:
     * The most actual Bossa csv files are downloaded from external resource in zip format by URL.
     * If external resource is unavailable (eg. internet connection problem),
     * csv files are fetched from app resources (in such case DataContainer stores data
     * up to day: 2017-08-27).
     * <p>
     * Note:
     * DataContainerBuilder works in two modes: 'demo' and 'production'
     * <p>
     * DEMO MODE (simple deployment: application uses temporary folders)
     * set property to:
     * <p>
     * IS_DEMO_MODE = true
     * <p>
     * For production mode (requires deployment configuration: application uses folders
     * in explicit locations, defined in configuration.json)
     * set property to:
     * <p>
     * IS_DEMO_MODE = false
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(DataContainerBuilder.class);

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

    public DataContainerBuilder() {
        configuration = new ConfigurationProvider(CONFIGURATION_FILE_PATH)
                .getConfiguration();
    }

    public DataContainerBuilder(String ResourcesFilePath) {
        configuration = new ConfigurationProvider(ResourcesFilePath)
                .getConfiguration();
    }

    public DataContainer getDataContainer() {

        new BossaDataFilesProvidersLogic(configuration, IS_DEMO_MODE).getFilesInFailSafeMode();

        if (IS_DEMO_MODE) {
            currencyFilePaths = filePathsProvider.generateTempCurrencyFilePaths();
            fundFilePaths = filePathsProvider.generateTempFundFilePaths();
        } else {
            currencyFilePaths = filePathsProvider.generateCurrencyFilePaths(configuration);
            fundFilePaths = filePathsProvider.generateFundFilePaths(configuration);
        }

        this.buildFunds();
        this.buildCurrencies();

        LOGGER.info("DataContainer - total amount of investments:{}", investments.size());

        return new DataContainer(investmentFundBuilder.getNumberOfFunds(),
                investmentCurrencyBuilder.getNumberOfCurrencies(), investments);
    }

    private void buildCurrencies() {

        currencyFilePaths.forEach((FilePath filePath) -> {
            investmentCurrencyBuilder.createCurrenciesFromFile(filePath.getFilePath());
        });
        investmentCurrencies = investmentCurrencyBuilder.getCurrencies();
        investments.addAll(investmentCurrencies);
    }

    private void buildFunds() {

        fundFilePaths.forEach((FilePath filePath) -> {
            investmentFundBuilder.createFundsFromFile(filePath.getFilePath());
        });
        investmentFunds = investmentFundBuilder.getInvestmentFunds();
        investments.addAll(investmentFunds);
    }
}
