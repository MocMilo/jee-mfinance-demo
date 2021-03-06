package com.infoshareacademy.web.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.criteria.InvestmentRevenueCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class InvestmentRevenueCriteriaConverterUtilTest {
    private final BigDecimal capital = new BigDecimal(10000.00);
    private final DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    private final LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
    private final LocalDate SELL_DATE = LocalDate.parse("20090912", formatter);
    private final String investmentName = "CHF";
    private WebInvestmentRevenueCriteria webCriteria;

    @Before
    public void init() {
        webCriteria = new WebInvestmentRevenueCriteria();
        webCriteria.setInvestmentName(investmentName);
        webCriteria.setInvestedCapital(capital);
        webCriteria.setBuyDate(BUY_DATE);
        webCriteria.setSellDate(SELL_DATE);
    }

    @Test
    public void shouldReturnConvertedInvestmentRevenueCriteria() {
        InvestmentRevenueCriteria investmentRevenueCriteria = InvestmentRevenueCriteriaConverterUtil.convertFrom(webCriteria);
        assertThat(investmentRevenueCriteria.getInvestmentName(), is(equalTo(investmentName)));
        assertThat(investmentRevenueCriteria.getInvestedCapital(), is(equalTo(capital)));
        assertThat(investmentRevenueCriteria.getBuyDate(), is(equalTo(BUY_DATE)));
        assertThat(investmentRevenueCriteria.getSellDate(), is(equalTo(SELL_DATE)));
    }
}