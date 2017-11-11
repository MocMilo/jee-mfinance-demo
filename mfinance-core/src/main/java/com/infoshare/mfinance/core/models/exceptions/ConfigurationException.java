package com.infoshare.mfinance.core.models.exceptions;

public class ConfigurationException extends Exception {
    private final static String message = "Error creating configuration";

    public ConfigurationException(Throwable previousException) {
        super(message + previousException.getMessage());
    }
}