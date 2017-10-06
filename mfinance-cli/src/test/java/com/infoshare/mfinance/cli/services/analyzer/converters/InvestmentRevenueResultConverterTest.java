package com.infoshare.mfinance.cli.services.analyzer.converters;

import com.infoshare.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.mfinance.cli.model.results.IVRResult;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class InvestmentRevenueResultConverterTest {

    private BigDecimal moneyValue = new BigDecimal("100.00");
    private BigDecimal deltaValue = new BigDecimal("10.00");

    private InvestmentRevenueResultConverter converter = new InvestmentRevenueResultConverter();
    private IVRResult ivrResult;
    private InvestmentRevenueResult revenueResult;
    @Before
    public void init() {
        revenueResult = new InvestmentRevenueResult(moneyValue, deltaValue);

    }

    @Test
    public void convertFrom() throws Exception {

        ivrResult = converter.convertFrom(revenueResult);
        assertThat(ivrResult.getCapitalRevenueValue(), is(equalTo(moneyValue)));
        assertThat(ivrResult.getCapitalRevenueDeltaPrecentValue(), is(equalTo(deltaValue)));

    }

}