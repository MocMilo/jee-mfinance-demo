package com.infoshare.core.models.exceptions;

public class ConfigurationException extends RuntimeException {
    private final static String message = "Error creating the configuration: ";

    public ConfigurationException(Throwable previousException) {
        super(message + previousException.getMessage());
    }
}