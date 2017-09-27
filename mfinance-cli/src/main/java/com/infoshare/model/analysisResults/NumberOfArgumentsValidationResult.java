package com.infoshare.model.analysisResults;

public class NumberOfArgumentsValidationResult {

    boolean isValid;
    String expectedValue;
    String evaluatedValue;


    public boolean isValid() {
        return isValid;
    }

    public String getEvaluatedValue() {
        return evaluatedValue;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public NumberOfArgumentsValidationResult(boolean isValid, String expectedValue, String evaluatedValue) {
        this.isValid = isValid;
        this.expectedValue = expectedValue;
        this.evaluatedValue = evaluatedValue;
    }
}
