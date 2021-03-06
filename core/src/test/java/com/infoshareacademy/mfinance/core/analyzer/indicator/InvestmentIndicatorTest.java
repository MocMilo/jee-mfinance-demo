package com.infoshareacademy.mfinance.core.analyzer.indicator;


import com.infoshareacademy.mfinance.core.analyzer.InvestmentIndicator;
import com.infoshareacademy.mfinance.core.models.analyzer.criteria.IndicatorCriteria;
import com.infoshareacademy.mfinance.core.models.analyzer.results.IndicatorResult;
import com.infoshareacademy.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.models.bossa.Investment;
import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class InvestmentIndicatorTest {
    private DataContainer container;
    private List<Investment> investments = new ArrayList<>();
    private List<Quotation> quotations = new ArrayList<>();
    private final String investmentName = "CHF";

    @Before
    public void init() {
        Quotation quotation1 = new Quotation("CHF", LocalDateUtil.parseCSV("20090910"), BigDecimal.valueOf(4.50));
        Quotation  quotation2 = new Quotation("CHF", LocalDateUtil.parseCSV("20090911"), BigDecimal.valueOf(4.70));
        Quotation  quotation3 = new Quotation("CHF", LocalDateUtil.parseCSV("20090912"), BigDecimal.valueOf(4.60));
        quotation1.setDeltaClose(BigDecimal.valueOf(0.0000));
        quotation2.setDeltaClose(BigDecimal.valueOf(0.0425));
        quotation3.setDeltaClose(BigDecimal.valueOf(-0.0212));

        quotations.add(quotation1);
        quotations.add(quotation2);
        quotations.add(quotation3);
        InvestmentCurrency investmentCurrency = new InvestmentCurrency("CHF", quotations);
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
    public void shouldReturnNotEmptyAnalysisResult() throws NoDataForCriteria {
        IndicatorCriteria criteria = new IndicatorCriteria(investmentName);
        IndicatorResult result = new InvestmentIndicator(container, criteria).getResult();

        assertThat(result.getFirstQuotation(), not(equalTo(nullValue())));
        assertThat(result.getName(), not(equalTo(nullValue())));
        assertThat(result.getActualValue(), not(equalTo(nullValue())));
        assertThat(result.getLastQuotation(), not(equalTo(nullValue())));
        assertThat(result.getMaxDeltaMinus(), not(equalTo(nullValue())));
        assertThat(result.getMaxDeltaPlus(), not(equalTo(nullValue())));
        assertThat(result.getMaxValueQuotation(), not(equalTo(nullValue())));
        assertThat(result.getMinValueQuotation(), not(equalTo(nullValue())));
    }

    @Test
    public void shouldReturnValidAnalysisResult() throws NoDataForCriteria {

        IndicatorCriteria criteria = new IndicatorCriteria(investmentName);
        IndicatorResult result = new InvestmentIndicator(container, criteria).getResult();

        assertThat(result.getFirstQuotation().getDate(), (equalTo(LocalDateUtil.parseCSV("20090910"))));
        assertThat(result.getFirstQuotation().getClose(), (equalTo(BigDecimal.valueOf(4.50))));
        assertThat(result.getFirstQuotation().getDeltaClose(), (equalTo(BigDecimal.valueOf(0.0000))));

        assertThat(result.getLastQuotation().getDate(), (equalTo(LocalDateUtil.parseCSV("20090912"))));
        assertThat(result.getLastQuotation().getClose(), (equalTo(BigDecimal.valueOf(4.60))));
        assertThat(result.getLastQuotation().getDeltaClose(), (equalTo(BigDecimal.valueOf(-0.0212))));

        assertThat(result.getMaxDeltaMinus().getDate(), (equalTo(LocalDateUtil.parseCSV("20090912"))));
        assertThat(result.getMaxDeltaMinus().getClose(), (equalTo(BigDecimal.valueOf(4.60))));
        assertThat(result.getMaxDeltaMinus().getDeltaClose(), (equalTo(BigDecimal.valueOf(-0.0212))));

        assertThat(result.getMaxDeltaPlus().getDate(), (equalTo(LocalDateUtil.parseCSV("20090911"))));
        assertThat(result.getMaxDeltaPlus().getClose(), (equalTo(BigDecimal.valueOf(4.70))));
        assertThat(result.getMaxDeltaPlus().getDeltaClose(), (equalTo(BigDecimal.valueOf(0.0425))));

        assertThat(result.getMinValueQuotation().getDate(), (equalTo(LocalDateUtil.parseCSV("20090910"))));
        assertThat(result.getMinValueQuotation().getClose(), (equalTo(BigDecimal.valueOf(4.50))));
        assertThat(result.getMinValueQuotation().getDeltaClose(), (equalTo(BigDecimal.valueOf(0.0000))));

        assertThat(result.getMaxValueQuotation().getDate(), (equalTo(LocalDateUtil.parseCSV("20090911"))));
        assertThat(result.getMaxValueQuotation().getClose(), (equalTo(BigDecimal.valueOf(4.70))));
        assertThat(result.getMaxValueQuotation().getDeltaClose(), (equalTo(BigDecimal.valueOf(0.0425))));
    }

    @Test(expected = NoDataForCriteria.class)
    public void shouldFailWhenNoQuotationsPresentInInvestments() throws NoDataForCriteria {
        container.getInvestments().forEach(x -> x.setQuotations(null));
        IndicatorCriteria criteria = new IndicatorCriteria(investmentName);
        new InvestmentIndicator(container, criteria).getResult();
    }

    @Test(expected = NoDataForCriteria.class)
    public void shouldFailWhenNotExistingInvestmentName() throws NoDataForCriteria {
        String notExistingInvestmentName = "XYZ";
        IndicatorCriteria criteria = new IndicatorCriteria(notExistingInvestmentName);
        new InvestmentIndicator(container, criteria).getResult();
    }
}