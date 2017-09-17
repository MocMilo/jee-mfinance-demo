package com.infoshare.core.models.exceptions;

public class AppConfigurationProviderException extends RuntimeException {
    private final static String message = "Error creating the configuration: ";

    public AppConfigurationProviderException(Throwable previousException) {
        super(message + previousException.getMessage());
    }
}