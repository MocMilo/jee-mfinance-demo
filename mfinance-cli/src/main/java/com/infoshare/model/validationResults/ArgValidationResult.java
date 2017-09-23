package com.infoshare.model.validationResults;

public class ArgValidationResult {

    private boolean isValid;
    private String errMessage;
    private String evaluatedValue;

    public boolean isValid() {
        return isValid;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public String getEvaluatedValue() {
        return evaluatedValue;
    }

    public ArgValidationResult(boolean isValid, String evaluatedValue, String errMessage) {
        this.isValid = isValid;
        this.errMessage = errMessage;
        this.evaluatedValue = evaluatedValue;
    }
}
