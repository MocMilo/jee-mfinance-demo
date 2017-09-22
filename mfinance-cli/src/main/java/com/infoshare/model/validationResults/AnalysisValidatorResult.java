package com.infoshare.model.validationResults;

public class AnalysisValidatorResult {

    private boolean isValid;
    private String errMessage;
    private String[] evaluatedArgs;

    public boolean isValid() {
        return isValid;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public String[] getEvaluatedArgs() {
        return evaluatedArgs;
    }

    public AnalysisValidatorResult(boolean isValid, String errMessage, String[] evaluatedArgs) {
        this.isValid = isValid;
        this.errMessage = errMessage;
        this.evaluatedArgs = evaluatedArgs;
    }
}
