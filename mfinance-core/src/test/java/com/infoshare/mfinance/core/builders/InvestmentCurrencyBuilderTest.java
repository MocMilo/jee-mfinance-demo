package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.models.bossa.InvestmentCurrency;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class InvestmentCurrencyBuilderTest {

    private String TESTFILEPATH = Paths.get("src/test/resources/csv/currencies/CHF.txt").toString();
    private final int NUMBEROFROWS = 6133;
    private InvestmentCurrency investmentCurrency;
    private CurrencyBuilder currencyBuilder;

    @Before
    public void init() {
        currencyBuilder = new CurrencyBuilder();
        currencyBuilder.createCurrenciesFromFile(TESTFILEPATH);
        investmentCurrency = currencyBuilder.getCurrencies().get(0);
    }

    @Test
    public void shouldCreateCurrenciesFromCSVFiles() throws Exception {

        int expected = 1;
        int evaluated = currencyBuilder.getNumberOfCurrencies();

        assertThat(investmentCurrency, not(equalTo(nullValue())));
        assertThat(evaluated, is(equalTo(expected)));
    }

    @Test
    public void shouldCurrencyContainLoadedData() throws Exception {

        String expectedName = "CHF";

        assertThat(investmentCurrency.getName(), is(equalTo(expectedName)));
        assertThat(investmentCurrency.getQuotations(), not(equalTo(nullValue())));
    }

    @Test
    public void shouldCurrencyContainLoadedQuotationData() throws Exception {

        int numberOfQuotations = currencyBuilder.getCurrencies().get(0).getQuotations().size();

        assertThat(NUMBEROFROWS, is(numberOfQuotations));
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenFileNotFound(){

        // fixme (should throw exception)

        String notExistingFilePath = "/NotExistingFilePath";
        new CurrencyBuilder().createCurrenciesFromFile(notExistingFilePath);
    }
}