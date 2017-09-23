package com.infoshare.model.validationResults;

import com.infoshare.model.arguments.IVRArgs;

public class AnalysisValidationResult {

    private boolean isValid;
    private String errMessage;
    private IVRArgs ivrArgs;

    public boolean isValid() {
        return isValid;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public IVRArgs getIvrArgs() {
        return ivrArgs;
    }

    public AnalysisValidationResult(boolean isValid, String errMessage, IVRArgs ivrArgs) {
        this.isValid = isValid;
        this.errMessage = errMessage;
        this.ivrArgs = ivrArgs;
    }
}
