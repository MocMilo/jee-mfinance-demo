package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.configuration.ConfigurationProvider;
import com.infoshare.mfinance.core.file.path.FilePath;
import com.infoshare.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshare.mfinance.core.models.bossa.InvestmentFund;
import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.mfinance.core.models.bossa.DataContainer;
import com.infoshare.mfinance.core.models.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;



public class DataContainerBuilder {

    /**
     * DEMO version (with simple deployment) set property to:
     *
     * IS_DEMO_MODE = true
     * (in this mode application reads csv files from application resources)
     *
     * For production deployment set property to:
     *
     * IS_DEMO_MODE = false
     * (in this mode application reads csv files from paths defined in Configuration.json)
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(DataContainerBuilder.class);
    private static final boolean IS_DEMO_MODE = true;
    private static final String CURRENCY_DEMO_RESOURCE_PATH = "bossademo/currencies/20170827_omeganbp.zip";
    private static final String FUND_DEMO_RESOURCE_PATH = "bossademo/funds/20170827_omegafun.zip";

    private ClassLoader classLoader = getClass().getClassLoader();
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
            configuration = new ConfigurationProvider().getDefaultConfiguration();
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
            InputStream inputStream = classLoader.getResourceAsStream(CURRENCY_DEMO_RESOURCE_PATH);

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            Path tempFilePath = Files.createTempFile("temp",".zip");

            OutputStream outStream = new FileOutputStream(tempFilePath.toFile());
            outStream.write(buffer);
            outStream.close();

            LOGGER.debug("Currencies Zip buffer length: "+ buffer.length);
            LOGGER.debug("file length bytes: "+ tempFilePath.toFile().length());
            LOGGER.debug("absolute file path: "+ tempFilePath.toFile().getAbsolutePath());

            /* fixme
             Fails when used in mfinance-cli module (when passing args from console).
             Fails exactly in line below, when tries to create zip file from temp zip file.
            */

            ZipFile zipFile = new ZipFile(tempFilePath.toFile().getAbsolutePath());

            LOGGER.debug("ZipFile size: "+ zipFile.size());

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
            throw new RuntimeException("Failed to parse currencies from Zip file:" + e.getMessage());
        }
    }

    private void buildFundsFromAppResourcesFiles() {
        try {
            InputStream inputStream = classLoader.getResourceAsStream(FUND_DEMO_RESOURCE_PATH);

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            Path tempFilePath = Files.createTempFile("temp",".zip");

            OutputStream outStream = new FileOutputStream(tempFilePath.toFile());
            outStream.write(buffer);
            outStream.close();

            LOGGER.debug("Funds Zip buffer length: "+ buffer.length);
            LOGGER.debug("file length bytes: "+ tempFilePath.toFile().length());
            LOGGER.debug("absolute file path: "+ tempFilePath.toFile().getAbsolutePath());

            /* fixme
             Fails when used in mfinance-cli module (when passing args from console).
             Fails exactly in line below, when tries to create zip file from temp zip file.
            */

            ZipFile zipFile = new ZipFile(tempFilePath.toFile());

            LOGGER.debug("ZipFile size: "+ zipFile.size());

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
            throw new RuntimeException("Failed to parse funds from Zip file:" + e.getMessage());
        }

    }
}
