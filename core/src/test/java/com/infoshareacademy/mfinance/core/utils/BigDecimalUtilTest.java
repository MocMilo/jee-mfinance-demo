package com.infoshareacademy.mfinance.core.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class BigDecimalUtilTest {

    @Test
    public void shouldReturnValidCSVBigDecimalValue()  {
        String validChangeRateString = "4.1234";
        BigDecimal validExchangeRateBigDecimal = new BigDecimal("4.1234")
                .setScale(4, RoundingMode.HALF_EVEN);
        BigDecimal parsedValue = BigDecimalUtil.parseCSV(validChangeRateString);
        assertThat(parsedValue, is(equalTo(validExchangeRateBigDecimal)));
    }

    @Test
    public void shouldReturnValidFormMoneyBigDecimalValue() {
        String validMoneyValueString = "1000.00";
        BigDecimal validMoneyValueBigDecimal = new BigDecimal("1000.00")
                .setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal parsedValue = BigDecimalUtil.parseFormMoney(validMoneyValueString);
        assertThat(parsedValue, is(equalTo(validMoneyValueBigDecimal)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailParseCSVWhenParsingInteger()  {
        String invalidNumber = "4";
        BigDecimalUtil.parseCSV(invalidNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailParseFormMoneyWhenParsingInteger() {
        String invalidNumber = "100";
        BigDecimalUtil.parseFormMoney(invalidNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailParseShorterScaleCSV()  {
        String invalidScale = "4.123";
        BigDecimalUtil.parseCSV(invalidScale);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailParseShorterScaleMoney()  {
        String invalidScale  = "100.1";
        BigDecimalUtil.parseFormMoney(invalidScale );
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailParseLongerScaleCSV()  {
        String invalidScale = "100.12345";
        BigDecimalUtil.parseCSV(invalidScale);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailParseLongerScaleMoney()  {
        String invalidScale = "100.123";
        BigDecimalUtil.parseFormMoney(invalidScale);
    }
}