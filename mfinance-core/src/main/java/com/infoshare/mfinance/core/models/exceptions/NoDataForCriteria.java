package com.infoshare.mfinance.core.models.exceptions;

public class NoDataForCriteria extends Exception {

    private final static String message = "No data for current criteria.";

    public NoDataForCriteria() {
        super(message);
    }

    public NoDataForCriteria(String message) {
        super(message);
    }


}