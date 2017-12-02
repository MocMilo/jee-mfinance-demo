package com.infoshareacademy.web.model.analyzer.results;

public abstract class WebAnalysisResult {
    protected String strategy;
    protected String errorMessage;

    public String getStrategy() {
        return strategy;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
