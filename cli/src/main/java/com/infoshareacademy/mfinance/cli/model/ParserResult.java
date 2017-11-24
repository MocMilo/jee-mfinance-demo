package com.infoshareacademy.mfinance.cli.model;

import com.infoshareacademy.mfinance.cli.model.arguments.ApplicationArguments;

public class ParserResult {

    private boolean isValid;
    private String errorMessage;
    private ApplicationArguments arguments;

    public boolean isValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ApplicationArguments getArguments() {
        return arguments;
    }

    public ParserResult(boolean isValid, String errorMessage, ApplicationArguments arguments) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
        this.arguments = arguments;
    }
}
