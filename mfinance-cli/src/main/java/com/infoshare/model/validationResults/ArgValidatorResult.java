package com.infoshare.model.validationResults;

public class ArgValidatorResult {

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

    public ArgValidatorResult(boolean isValid, String evaluatedValue, String errMessage) {
        this.isValid = isValid;
        this.errMessage = errMessage;
        this.evaluatedValue = evaluatedValue;
    }
}
