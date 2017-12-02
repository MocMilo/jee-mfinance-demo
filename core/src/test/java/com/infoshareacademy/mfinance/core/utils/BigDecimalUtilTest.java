package com.infoshareacademy.mfinance.core.utils;

import org.junit.Test;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class BigDecimalUtilTest {

    @Test
    public void shouldReturnValidExchangeRateBigDecimalValue() {
        String validExchangeRateString = "4.1234";
        BigDecimal validExchangeRateBigDecimal = new BigDecimal("4.1234").setScale(4);
        BigDecimal parsedValue = BigDecimalUtil.parseExchangeRate(validExchangeRateString);
        assertThat(parsedValue, is(equalTo(validExchangeRateBigDecimal)));
    }

    @Test
    public void shouldReturnValidExchangeRateBigDecimalValueWhenParsingInteger() {
        String validExchangeRateString = "4";
        BigDecimal expected = new BigDecimal("4.0000").setScale(4);
        BigDecimal parsedValue = BigDecimalUtil.parseExchangeRate(validExchangeRateString);
        assertThat(parsedValue, is(equalTo(expected)));
    }

    @Test
    public void shouldParseShorterScaleExchangeRate() {
        String exchangeRateStringShorterScale = "4.123";
        BigDecimal expected = new BigDecimal("4.1230").setScale(4);
        BigDecimal parsedValue = BigDecimalUtil.parseExchangeRate(exchangeRateStringShorterScale);
        assertThat(parsedValue, is(equalTo(expected)));
    }

    @Test
    public void shouldReturnValidMoneyBigDecimalValue() {
        String validMoneyValueString = "1000.00";
        BigDecimal validMoneyValueBigDecimal = new BigDecimal("1000.00").setScale(2);
        BigDecimal parsedValue = BigDecimalUtil.parseMoney(validMoneyValueString);
        assertThat(parsedValue, is(equalTo(validMoneyValueBigDecimal)));
    }

    @Test
    public void shouldReturnValidMoneyBigDecimalValueWhenParsingInteger() {
        String validExchangeRateString = "100";
        BigDecimal expected = new BigDecimal("100.00").setScale(2);
        BigDecimal parsedValue = BigDecimalUtil.parseMoney(validExchangeRateString);
        assertThat(parsedValue, is(equalTo(expected)));
    }

    @Test
    public void shouldParseShorterScaleMoneyBigDecimalValue() {
        String exchangeRateStringShorterScale = "100.1";
        BigDecimal expected = new BigDecimal("100.10").setScale(2);
        BigDecimal parsedValue = BigDecimalUtil.parseMoney(exchangeRateStringShorterScale);
        assertThat(parsedValue, is(equalTo(expected)));
    }

    // @Test(Expected = ParseException.class) TODO
    public void shouldNotParseWrongDecimalPointCharacterOfExchangeRate() {
        String exchangeRateInvalidString = "4,1234";
        BigDecimal parsedValue = BigDecimalUtil.parseExchangeRate(exchangeRateInvalidString);
    }

    //@Test(Expected = ParseException.class) TODO
    public void shouldNotParseExchangeRateOfBiggerScaleThanExpected() {
        String exchangeRateStringOfBiggerScale = "4.12345";
        BigDecimal parsedValue = BigDecimalUtil.parseExchangeRate(exchangeRateStringOfBiggerScale);
    }
}