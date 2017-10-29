package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.configuration.ConfigurationProvider;
import com.infoshare.mfinance.core.file.path.FilePath;
import com.infoshare.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshare.mfinance.core.models.bossa.InvestmentFund;
import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.mfinance.core.models.bossa.DataContainer;
import com.infoshare.mfinance.core.models.configuration.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class DataContainerBuilder {

    /**
     * To simplify deployment of DEMO version set property to:
     * IS_DEMO_MODE = true
     * (in this mode application reads csv files from application resources)
     * <p>
     * For production deployment set property to:
     * IS_DEMO_MODE = false
     * (in this mode application reads csv files from path defined in Configuration.json)
     */

    private static final boolean IS_DEMO_MODE = true;
    private static final String CURRENCY_DEMO_RESOURCE_PATH = "bossademo/currencies/20170827_omeganbp.zip";
    private static final String FUND_DEMO_RESOURCE_PATH = "bossademo/funds/20170827_omegafun.zip";

    ClassLoader classLoader = getClass().getClassLoader();
    private Configuration configuration;


    private DataContainer dataContainer = new DataContainer();
    private FundBuilder fundBuilder = new FundBuilder();
    private CurrencyBuilder currencyBuilder = new CurrencyBuilder();

    private List<InvestmentFund> investmentFunds = new ArrayList<>();
    private List<InvestmentCurrency> currencies = new ArrayList<>();
    private List<Investment> investments = new ArrayList<>();

    public DataContainer getDataContainer() {

        if (IS_DEMO_MODE) {
            this.buildCurrenciesFromAppResourcesFiles();
            this.buildFundsFromAppResourcesFiles();
        }

        if (!IS_DEMO_MODE) {
            configuration = new ConfigurationProvider().getConfiguration();
            this.buildFunds();
            this.buildCurrencies();
        }

        dataContainer.setInvestments(investments);
        dataContainer.setFundsCount(fundBuilder.getNumberOfFunds());
        dataContainer.setCurrenciesCount(currencyBuilder.getNumberOfCurrencies());

        return dataContainer;
    }

    private void buildCurrencies() {
        configuration.getCurrencyFilePaths()
                .forEach((FilePath filePath) -> {
                    currencyBuilder.createCurrenciesFromFile(filePath.getFilePath());
                });
        currencies = currencyBuilder.getCurrencies();
        investments.addAll(currencies);
    }

    private void buildFunds() {
        configuration.getFundFilePaths().forEach((FilePath filePath) -> {
            fundBuilder.createFundsFromFile(filePath.getFilePath());
        });
        investmentFunds = fundBuilder.getInvestmentFunds();
        investments.addAll(investmentFunds);
    }

    private void buildCurrenciesFromAppResourcesFiles() {
        try {

            File file = new File(classLoader.getResource(CURRENCY_DEMO_RESOURCE_PATH).getFile());

            ZipFile zipFile = new ZipFile(file);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                InputStream stream = zipFile.getInputStream(entry);

                currencyBuilder.createCurrenciesFromStream(stream);
                stream.close();
            }
            zipFile.close();
            currencies = currencyBuilder.getCurrencies();
            investments.addAll(currencies);

        } catch (IOException e) {
            throw new RuntimeException("Faild to parse currencies from Zip file:" + e.getMessage());
        }
    }

    private void buildFundsFromAppResourcesFiles() {
        try {
            File file = new File(classLoader.getResource(FUND_DEMO_RESOURCE_PATH).getFile());

            ZipFile zipFile = new ZipFile(file);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                InputStream stream = zipFile.getInputStream(entry);

                fundBuilder.createFundsFromStream(stream);
                stream.close();
            }
            zipFile.close();

            investmentFunds = fundBuilder.getInvestmentFunds();
            investments.addAll(investmentFunds);

        } catch (IOException e) {
            throw new RuntimeException("Faild to parse funds from Zip file:" + e.getMessage());
        }
    }
}
