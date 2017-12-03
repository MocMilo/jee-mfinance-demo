package com.infoshareacademy.mfinance.core.models.exceptions;

public class NoDataForCriteria extends Exception {
    /**
     * This custom exception is checked exception. User is not aware
     * of possible quotation data unavailability in DataContainer (Bossa csv files).
     * For example: user analysis criteria may contain dates out of range
     * (current investment had no quotations in current period, but user defined
     * this period in his analysis criteria - not being aware of data unavailability).
     */
    private final static String message = "No data for current criteria.";

    public NoDataForCriteria() {
        super(message);
    }

    public NoDataForCriteria(String message) {
        super(message);
    }
}