package com.infoshareacademy.mfinance.core.utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class BigDecimalUtil {
    private static final String BIG_DECIMAL_PATTERN = "#0.0#";
    private static BigDecimal parsedByFormatter;
    private static DecimalFormat decimalFormat;

    public static BigDecimal parseExchangeRate(String value) {
        try {
            setDecimalFormat();
            parsedByFormatter = (BigDecimal) decimalFormat.parse(value);
            return parsedByFormatter.setScale(4);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    public static BigDecimal parseMoney(String value) {
        try {
            setDecimalFormat();
            parsedByFormatter = (BigDecimal) decimalFormat.parse(value);
            return parsedByFormatter.setScale(2);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void setDecimalFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        decimalFormat = new DecimalFormat(BIG_DECIMAL_PATTERN, symbols);
        decimalFormat.setParseBigDecimal(true);
    }
}
