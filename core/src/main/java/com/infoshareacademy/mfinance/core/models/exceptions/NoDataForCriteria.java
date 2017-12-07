package com.infoshareacademy.mfinance.core.models.exceptions;
/**
 * This exception is 'checked'.
 * business example: "It is normal that current investment may not have
 * quotations in some periods of time". User analysis criteria
 * may contain dates 'out of range' and user is 'not aware'
 * of data unavailability for chosen period of time).
 */
public class NoDataForCriteria extends Exception {

    private final static String message = "No data for current criteria.";

    public NoDataForCriteria() {
        super(message);
    }

    public NoDataForCriteria(String message) {
        super(message);
    }
}