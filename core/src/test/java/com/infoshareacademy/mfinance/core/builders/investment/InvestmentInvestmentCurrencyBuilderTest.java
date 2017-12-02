package com.infoshareacademy.mfinance.core.builders.investment;

import com.infoshareacademy.mfinance.core.models.bossa.InvestmentCurrency;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class InvestmentInvestmentCurrencyBuilderTest {

    private final String TEST_FILE_PATH = Paths.get("src/test/resources/csv/currencies/CHF-test.txt").toString();
    private final int NUMBER_OF_ROWS = 6133;
    private InvestmentCurrency investmentCurrency;
    private InvestmentCurrencyListBuilder investmentCurrencyBuilder;

    @Before
    public void init() {
        investmentCurrencyBuilder = new InvestmentCurrencyListBuilder();
        investmentCurrencyBuilder.createCurrenciesFromFile(TEST_FILE_PATH);
        investmentCurrency = investmentCurrencyBuilder.getCurrencies().get(0);
    }

    @Test
    public void shouldCreateCurrenciesFromCSVFiles() {
        int expected = 1;
        int evaluated = investmentCurrencyBuilder.getNumberOfCurrencies();
        assertThat(investmentCurrency, not(equalTo(nullValue())));
        assertThat(evaluated, is(equalTo(expected)));
    }

    @Test
    public void shouldCurrencyContainLoadedData() {
        String expectedName = "CHF";
        assertThat(investmentCurrency.getName(), is(equalTo(expectedName)));
        assertThat(investmentCurrency.getQuotations(), not(equalTo(nullValue())));
    }

    @Test
    public void shouldCurrencyContainLoadedQuotationData() {
        int numberOfQuotations = investmentCurrencyBuilder.getCurrencies().get(0).getQuotations().size();
        assertThat(NUMBER_OF_ROWS, is(numberOfQuotations));
    }
}