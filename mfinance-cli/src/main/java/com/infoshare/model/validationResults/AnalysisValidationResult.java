package com.infoshare.model.validationResults;


public class AnalysisValidationResult {

    private boolean isValid;
    private String errMessage;
    private String[] validatedArgs;

    public boolean isValid() {
        return isValid;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public String[] getValidatedArgs() {
        return validatedArgs;
    }

    public AnalysisValidationResult(boolean isValid, String errMessage, String[] validatedArgs) {
        this.isValid = isValid;
        this.errMessage = errMessage;
        this.validatedArgs = validatedArgs;
    }




}
