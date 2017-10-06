package com.infoshare.core.analyzer.analyses.revenue;

import com.infoshare.core.configuration.ConfigurationProvider;
import com.infoshare.core.loader.MainContainerLoader;
import com.infoshare.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.configuration.Configuration;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


public class InvestmentRevenueTest {

    private static final Configuration configuration = new ConfigurationProvider().getConfiguration();
    private static final MainContainerLoader mainContainerLoader = new MainContainerLoader(configuration);

    private final BigDecimal capital = new BigDecimal(10000.00);
    private final DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    private final LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
    private final LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);
    private final String InvestmentName = "CHF";

    @BeforeClass
    public static void init() {
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();
    }

    @Test
    public void getInvestmentsFfomMainContainer() {

        MainContainer container = mainContainerLoader.getMainContainer();
        int investments = container.getInvestments().size();

        assertThat(investments, not(equalTo(nullValue())));

        System.out.println("number of investments: " + investments);
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenUserInputOutOfRange() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20140104", formatter);

        MainContainer mc = mainContainerLoader.getMainContainer();

        // example analyses usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();

        //analyses input
        assertThat(input.getBuyDate(), not(equalTo(nullValue())));
        assertThat(input.getSellDate(), not(equalTo(nullValue())));

        // anlysis results
        assertThat(ir.getCapitalRevenueValue(), is(equalTo(nullValue())));
        assertThat(ir.getCapitalRevenueDeltaPercentValue(), is(equalTo(nullValue())));
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenMissingQuotations() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);

        MainContainer mc = mainContainerLoader.getMainContainer();

        // ESSENTIAL: removing quotations
        mc.getInvestments().forEach(x -> x.setQuotations(null));

        // example analyses usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
    }
}