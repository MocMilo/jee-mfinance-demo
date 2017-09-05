package com.infoshare.core.analyzer.analyses;

public abstract class AnalysisResult {

    protected String returnMessage;
    protected AnalysisCriteria finallyEvaluatedInput;


    public AnalysisCriteria getFinallyEvaluatedInput() {
        return finallyEvaluatedInput;
    }

    public void setFinallyEvaluatedInput(AnalysisCriteria finallyEvaluatedInput) {
        this.finallyEvaluatedInput = finallyEvaluatedInput;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
