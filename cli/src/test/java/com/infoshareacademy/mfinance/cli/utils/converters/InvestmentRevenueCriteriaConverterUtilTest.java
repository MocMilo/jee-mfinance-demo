package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.cli.model.arguments.IVRArgs;
import com.infoshareacademy.mfinance.core.models.analyzer.criteria.InvestmentRevenueCriteria;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class InvestmentRevenueCriteriaConverterUtilTest {
    private IVRArgs ivrArgs;

    @Before
    public void init() {
        final String[] args = {"IVR", "USD", "1000.00", "2015-09-08", "2015-09-07"};
        ivrArgs = new IVRArgs(args);
    }

    @Test
    public void convertFrom() throws Exception {
        String investmentName = "USD";
        BigDecimal investmentValue = new BigDecimal("1000.00");
        LocalDate buyDate = LocalDate.parse("2015-09-08");
        LocalDate sellDate = LocalDate.parse("2015-09-07");

        InvestmentRevenueCriteria criteria = InvestmentRevenueCriteriaConverterUtil
                .convertFrom(ivrArgs);

        assertThat(criteria.getInvestmentName(), is(equalTo(investmentName)));
        assertThat(criteria.getInvestedCapital(), is(equalTo(investmentValue)));
        assertThat(criteria.getBuyDate(), is(equalTo(buyDate)));
        assertThat(criteria.getSellDate(), is(equalTo(sellDate)));
    }
}