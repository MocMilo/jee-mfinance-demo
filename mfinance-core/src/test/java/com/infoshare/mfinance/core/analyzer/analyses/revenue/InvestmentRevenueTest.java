package com.infoshare.mfinance.core.analyzer.analyses.revenue;

import com.infoshare.mfinance.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.mfinance.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.mfinance.core.models.bossa.Currency;
import com.infoshare.mfinance.core.models.bossa.DataContainer;
import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.mfinance.core.models.bossa.Quotation;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
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

    private DataContainer container = new DataContainer();
    private List<Investment> investments = new ArrayList<>();
    private List<Quotation> quotations = new ArrayList<>();
    private Quotation quotation1;
    private Quotation quotation2;
    private Quotation quotation3;
    private Currency currency;

    private final BigDecimal capital = new BigDecimal(10000.00);
    private final DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    private final LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
    private final LocalDate SELL_DATE = LocalDate.parse("20090912", formatter);
    private final String InvestmentName = "CHF";

    @Before
    public void init() {
        quotation1 = new Quotation("CHF", LocalDate.parse("20090910", formatter), new BigDecimal(4.50));
        quotation2 = new Quotation("CHF", LocalDate.parse("20090911", formatter), new BigDecimal(4.60));
        quotation3 = new Quotation("CHF", LocalDate.parse("20090912", formatter), new BigDecimal(4.70));

        quotations.add(quotation1);
        quotations.add(quotation2);
        quotations.add(quotation3);

        currency = new Currency("CHF", quotations);
        investments.add(currency);
        container.setInvestments(investments);
        container.setCurrenciesCount(1);
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

        assertThat(result.getCapitalRevenueValue(), is(equalTo(new BigDecimal("400.00"))));
        assertThat(result.getCapitalRevenueDeltaPercentValue(), is(equalTo(new BigDecimal("4.4444"))));
    }

    @Test(expected = NoDataForCriteria.class)
    public void shouldFailWhenCriteriaSellDateOutOfRange() throws NoDataForCriteria {

        LocalDate SELL_DATE = LocalDate.parse("20140104", formatter);
        InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        new InvestmentRevenue(container, criteria).getResult();
    }

    @Test(expected = NoDataForCriteria.class)
    public void shouldFailWhenCriteriaBuyDateOutOfRange() throws NoDataForCriteria {

        LocalDate BUY_DATE = LocalDate.parse("20060104", formatter);
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