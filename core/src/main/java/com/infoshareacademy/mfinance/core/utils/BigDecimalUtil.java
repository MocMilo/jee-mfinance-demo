package com.infoshareacademy.mfinance.core.utils;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.apache.commons.validator.routines.RegexValidator;

import java.math.BigDecimal;

public class BigDecimalUtil {
    private static final String CSV_BIG_DECIMAL_PATTERN = "#0.0000";
    private static final String MONEY_BIG_DECIMAL_PATTERN = "#0.00";

    private static final RegexValidator regexCSVValidator = new RegexValidator("^(0|0?[1-9]\\d*)\\.\\d\\d\\d\\d$", false);
    private static final RegexValidator regexMoneyValidator = new RegexValidator("^(0|0?[1-9]\\d*)\\.\\d\\d$", false);

    private static BigDecimalValidator currencyFormValidator = CurrencyValidator.getInstance();
    private static BigDecimalValidator csvValidator = BigDecimalValidator.getInstance();

    public static BigDecimal parseCSV(String value) {
        if (!regexMoneyValidator.isValid(value) && !regexCSVValidator.isValid(value)) {
            throw new IllegalArgumentException();
        }
        BigDecimal parsedByValidator = csvValidator.validate(value, CSV_BIG_DECIMAL_PATTERN);
        if (parsedByValidator == null) {
            throw new IllegalArgumentException();
        }
        return parsedByValidator;
    }

    public static BigDecimal parseFormMoney(String value) {
        if (!regexMoneyValidator.isValid(value)) {
            throw new IllegalArgumentException();
        }
        BigDecimal moneyValue = currencyFormValidator.validate(value, MONEY_BIG_DECIMAL_PATTERN);
        if (moneyValue == null) {
            throw new IllegalArgumentException();
        }
        return moneyValue;
    }
}
