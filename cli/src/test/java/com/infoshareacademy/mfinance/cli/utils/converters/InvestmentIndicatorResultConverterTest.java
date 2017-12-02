package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.results.IndicatorResult;
import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import com.infoshareacademy.mfinance.cli.model.results.INDResult;
import com.infoshareacademy.mfinance.cli.model.results.embeded.CLIQuotation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InvestmentIndicatorResultConverterTest {
    private IndicatorResultConverter converter = new IndicatorResultConverter();

    private Quotation coreQuotation;
    private CLIQuotation cliQuotation;
    private IndicatorResult coreIndicatorResult;
    private INDResult cliIndicatorResult;

    private String name = "USD";
    private LocalDate date = LocalDate.parse("2015-09-08");
    private BigDecimal moneyValue = new BigDecimal("100.00");

    @Before
    public void init() {

        coreQuotation = mock(Quotation.class);
        when(coreQuotation.getName()).thenReturn(name);
        when(coreQuotation.getDate()).thenReturn(date);
        when(coreQuotation.getClose()).thenReturn(moneyValue);
        when(coreQuotation.getDeltaClose()).thenReturn(moneyValue);

        cliQuotation = mock(CLIQuotation.class);
        when(cliQuotation.getName()).thenReturn(name);
        when(cliQuotation.getDate()).thenReturn(date);
        when(cliQuotation.getClose()).thenReturn(moneyValue);
        when(cliQuotation.getDeltaClose()).thenReturn(moneyValue);

        coreIndicatorResult = mock(IndicatorResult.class);
        when(coreIndicatorResult.getName()).thenReturn(name);
        when(coreIndicatorResult.getActualValue()).thenReturn(coreQuotation);
        when(coreIndicatorResult.getFirstQuotation()).thenReturn(coreQuotation);
        when(coreIndicatorResult.getLastQuotation()).thenReturn(coreQuotation);
        when(coreIndicatorResult.getMaxDeltaPlus()).thenReturn(coreQuotation);
        when(coreIndicatorResult.getMaxDeltaMinus()).thenReturn(coreQuotation);
        when(coreIndicatorResult.getMaxValueQuotation()).thenReturn(coreQuotation);
        when(coreIndicatorResult.getMinValueQuotation()).thenReturn(coreQuotation);
    }

    @Test
    public void doConvert() {
        cliIndicatorResult = converter.convertFrom(coreIndicatorResult);
        assertThat(cliIndicatorResult.getName(), is(equalTo(coreIndicatorResult.getName())));
        assertThat(cliIndicatorResult.getFirstQuotation().getName(), is(equalTo(coreIndicatorResult.getFirstQuotation().getName())));

        assertThat(cliIndicatorResult.getFirstQuotation(), not(equalTo(nullValue())));
        assertThat(cliIndicatorResult.getLastQuotation(), not(equalTo(nullValue())));
        assertThat(cliIndicatorResult.getMaxDeltaPlus(), not(equalTo(nullValue())));
        assertThat(cliIndicatorResult.getMaxDeltaMinus(), not(equalTo(nullValue())));
        assertThat(cliIndicatorResult.getMaxValueQuotation(), not(equalTo(nullValue())));
        assertThat(cliIndicatorResult.getMinValueQuotation(), not(equalTo(nullValue())));
    }
}