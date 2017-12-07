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

/**
 * set property of DataContainerBuilder:
 * IS_DEMO_MODE = true (simple deployment: application uses temporary folders)
 * IS_DEMO_MODE = false (advanced deployment: requires configuration of explicit
 * work-folders, defined in resources/configuration.json file)
 */
public class DataContainerBuilder {
    private static final boolean IS_DEMO_MODE = true;
    private static final String CONFIGURATION_FILE_PATH = "configuration/configuration.json";

    private Configuration configuration;
    private InvestmentFundListBuilder investmentFundBuilder = new InvestmentFundListBuilder();
    private InvestmentCurrencyListBuilder investmentCurrencyBuilder = new InvestmentCurrencyListBuilder();

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

    /**
     * @return DataContainer - build from csv files (zip archives downloaded from URL).
     */
    public DataContainer getDataContainer() throws IOException {
        BossaDataFilesProvider.getCSVFiles(configuration, IS_DEMO_MODE);

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
        List<InvestmentCurrency> investmentCurrencies = investmentCurrencyBuilder.getCurrencies();
        investments.addAll(investmentCurrencies);
    }

    private void buildFunds() throws IOException {
        for (FilePath filePath : fundFilePaths) {
            investmentFundBuilder.createFundsFromFile(filePath.getFilePath());
        }
        List<InvestmentFund> investmentFunds = investmentFundBuilder.getInvestmentFunds();
        investments.addAll(investmentFunds);
    }
}
