package com.infoshareacademy.mfinance.core.analyzer.revenue;

import com.infoshareacademy.mfinance.core.analyzer.InvestmentRevenue;
import com.infoshareacademy.mfinance.core.models.analyzer.criteria.InvestmentRevenueCriteria;
import com.infoshareacademy.mfinance.core.models.analyzer.results.InvestmentRevenueResult;
import com.infoshareacademy.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.models.bossa.Investment;
import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.Is.is;

public class InvestmentRevenueTest {

    private DataContainer container;
    private List<Investment> investments = new ArrayList<>();
    private List<Quotation> quotations = new ArrayList<>();
    private Quotation quotation1;
    private Quotation quotation2;
    private Quotation quotation3;
    private InvestmentCurrency investmentCurrency;

    private final BigDecimal capital = new BigDecimal(10000.00);

    private final LocalDate BUY_DATE = LocalDateUtil.parseCSV("20090910");
    private final LocalDate SELL_DATE = LocalDateUtil.parseCSV("20090912");
    private final String InvestmentName = "CHF";

    @Before
    public void init() {

        quotation1 = new Quotation("CHF", LocalDateUtil.parseCSV("20090910"), BigDecimal.valueOf(4.50));
        quotation2 = new Quotation("CHF", LocalDateUtil.parseCSV("20090911"), BigDecimal.valueOf(4.70));
        quotation3 = new Quotation("CHF", LocalDateUtil.parseCSV("20090912"), BigDecimal.valueOf(4.60));

        quotations.add(quotation1);
        quotations.add(quotation2);
        quotations.add(quotation3);

        investmentCurrency = new InvestmentCurrency("CHF", quotations);
        investments.add(investmentCurrency);
        container = new DataContainer(0, 1, investments);
    }

    @Test
    public void shouldDataContainerNotBeEmpty() {

        int investments = container.getInvestments().size();
        assertThat(investments, not(equalTo(nullValue())));
    }

    @Test
    public void shouldDataContainerHaveValidInvestmentsSize() {

        int investments = container.getInvestments().size();
        assertThat(investments, is(equalTo(1)));
    }

    @Test
    public void shouldReturnNotEmptyAnalysisResult() throws NoDataForCriteria{

        InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenueResult result = new InvestmentRevenue(container, criteria).getResult();

        assertThat(result.getCapitalRevenueValue(), not(equalTo(nullValue())));
        assertThat(result.getCapitalRevenueDeltaPercentValue(), not(equalTo(nullValue())));
    }

    @Test
    public void shouldReturnValidAnalysisResult() throws NoDataForCriteria{

        InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenueResult result = new InvestmentRevenue(container, criteria).getResult();

        assertThat(result.getCapitalRevenueValue(), is(equalTo(new BigDecimal("200.00"))));
        assertThat(result.getCapitalRevenueDeltaPercentValue(), is(equalTo(new BigDecimal("2.2222"))));
    }

    @Test(expected = NoDataForCriteria.class)
    public void shouldFailWhenCriteriaSellDateOutOfRange() throws NoDataForCriteria {

        LocalDate SELL_DATE = LocalDateUtil.parseCSV("20140104");
        InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        new InvestmentRevenue(container, criteria).getResult();
    }

    @Test(expected = NoDataForCriteria.class)
    public void shouldFailWhenCriteriaBuyDateOutOfRange() throws NoDataForCriteria {

        LocalDate BUY_DATE = LocalDateUtil.parseCSV("20060104");
        InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        new InvestmentRevenue(container, criteria).getResult();
    }

    @Test(expected = NoDataForCriteria.class)
    public void shouldFailWhenMissingQuotations() throws NoDataForCriteria {

        container.getInvestments().forEach(x -> x.setQuotations(null));
        InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        new InvestmentRevenue(container, criteria).getResult();
    }
}