package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.models.locations.path.FilePath;
import com.infoshare.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshare.mfinance.core.models.bossa.InvestmentFund;
import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.mfinance.core.models.bossa.DataContainer;
import com.infoshare.mfinance.core.models.configuration.Configuration;
import com.infoshare.mfinance.core.builders.investment.InvestmentCurrencyListBuilder;
import com.infoshare.mfinance.core.builders.investment.InvestmentFundListBuilder;
import com.infoshare.mfinance.core.providers.ConfigurationProvider;
import com.infoshare.mfinance.core.providers.bossadata.DemoFilesProvider;
import com.infoshare.mfinance.core.providers.bossadata.FilePathsProvider;
import com.infoshare.mfinance.core.providers.bossadata.RemoteDataFilesProvider;
import com.infoshare.mfinance.core.providers.bossadata.ResourcesDataFilesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DataContainerBuilder {

    /**
     * @return DataContainer  - build from csv files
     *
     * Actual Bossa csv files are downloaded from external resource in zip format using URL.
     *
     * Note: if external resource is unavailable (eg. internet connection problem),
     * csv files are fetched from app resources (in this case DataContainer
     * store data up to day: 2017-08-27)
     *
     * DEMO MODE (simple deployment: application stores csv files in temporary folders)
     * set property to:
     *
     * IS_DEMO_MODE = true
     *
     * For production mode (requires deployment configuration: application stores csv files
     * in explicit locations, defined in configuration.json)
     * set property to:
     *
     * IS_DEMO_MODE = false
     *
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

        if (IS_DEMO_MODE) {

            new DemoFilesProvider(configuration).getCSVFiles();

            currencyFilePaths = filePathsProvider.generateTempCurrencyFilePaths();
            fundFilePaths = filePathsProvider.generateTempFundFilePaths();

            this.buildFunds();
            this.buildCurrencies();
        }

        if (!IS_DEMO_MODE) {

            new RemoteDataFilesProvider(configuration)
                    .saveFilesInExplicitFolders();

            currencyFilePaths = filePathsProvider.generateCurrencyFilePaths(configuration);
            fundFilePaths = filePathsProvider.generateFundFilePaths(configuration);

            this.buildFunds();
            this.buildCurrencies();
        }

        LOGGER.info("DataContainer - total amount of investments:{}", investments.size());

        return new DataContainer(investmentFundBuilder.getNumberOfFunds(),
                investmentCurrencyBuilder.getNumberOfCurrencies(),
                investments);
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
