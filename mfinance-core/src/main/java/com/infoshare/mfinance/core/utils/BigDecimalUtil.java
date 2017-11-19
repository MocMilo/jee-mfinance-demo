package com.infoshare.mfinance.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import static java.math.RoundingMode.HALF_EVEN;

public class BigDecimalUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BigDecimalUtil.class);

    private static BigDecimal parsedByFormatter;
    private static BigDecimal exchangeRate;
    private static BigDecimal money;

    private static final String BIG_DECIMAL_PATTERN = "#0.0#";
    private static DecimalFormat decimalFormat;

    public static BigDecimal parseExchangeRate(String value) {

        setDecimalFormat();

        try {
            parsedByFormatter = (BigDecimal) decimalFormat.parse(value);
            exchangeRate = parsedByFormatter.setScale(4);
        } catch (ParseException e) {
            LOGGER.error("Failed to parse ExchangeRate String:{} to BigDecimal. {}", value, e.getMessage());
        }
        return exchangeRate;
    }

    public static BigDecimal parseMoney(String value) {

        setDecimalFormat();

        try {
            parsedByFormatter = (BigDecimal) decimalFormat.parse(value);
            money = parsedByFormatter.setScale(2);
        } catch (ParseException e) {
            LOGGER.error("Failed to parse Money String:{} to BigDecimal. {}", value, e.getMessage());
        }
        return money;
    }

    private static void setDecimalFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        decimalFormat = new DecimalFormat(BIG_DECIMAL_PATTERN, symbols);
        decimalFormat.setParseBigDecimal(true);
    }
}
