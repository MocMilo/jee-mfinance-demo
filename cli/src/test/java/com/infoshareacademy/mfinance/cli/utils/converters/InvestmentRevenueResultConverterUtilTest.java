package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.cli.model.results.IVRResult;
import com.infoshareacademy.mfinance.core.models.analyzer.results.InvestmentRevenueResult;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class InvestmentRevenueResultConverterUtilTest {
    private final BigDecimal moneyValue = new BigDecimal("100.00");
    private final BigDecimal deltaValue = new BigDecimal("10.00");
    private InvestmentRevenueResult revenueResult;

    @Before
    public void init() {
        revenueResult = new InvestmentRevenueResult(moneyValue, deltaValue);
    }

    @Test
    public void convertFrom() throws Exception {
        IVRResult ivrResult = InvestmentRevenueResultConverterUtil.convertFrom(revenueResult);
        assertThat(ivrResult.getCapitalRevenueValue(), is(equalTo(moneyValue)));
        assertThat(ivrResult.getCapitalRevenueDeltaPrecentValue(), is(equalTo(deltaValue)));
    }
}