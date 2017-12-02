package com.infoshareacademy.web.model.analyzer.results;

public class WebIndicatorResult extends WebAnalysisResult {
    private static final String STRATEGY = "IND";
    private String name;
    private WebQuotation firstQuotation;
    private WebQuotation lastQuotation;
    private WebQuotation maxValueQuotation;
    private WebQuotation minValueQuotation;
    private WebQuotation maxDeltaPlus;
    private WebQuotation maxDeltaMinus;
    private WebQuotation actualValue;

    public WebIndicatorResult() {
    }

    public String getStrategy() {
        return STRATEGY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebQuotation getFirstQuotation() {
        return firstQuotation;
    }

    public void setFirstQuotation(WebQuotation firstQuotation) {
        this.firstQuotation = firstQuotation;
    }

    public WebQuotation getLastQuotation() {
        return lastQuotation;
    }

    public void setLastQuotation(WebQuotation lastQuotation) {
        this.lastQuotation = lastQuotation;
    }

    public WebQuotation getMaxValueQuotation() {
        return maxValueQuotation;
    }

    public void setMaxValueQuotation(WebQuotation maxValueQuotation) {
        this.maxValueQuotation = maxValueQuotation;
    }

    public WebQuotation getMinValueQuotation() {
        return minValueQuotation;
    }

    public void setMinValueQuotation(WebQuotation minValueQuotation) {
        this.minValueQuotation = minValueQuotation;
    }

    public WebQuotation getMaxDeltaPlus() {
        return maxDeltaPlus;
    }

    public void setMaxDeltaPlus(WebQuotation maxDeltaPlus) {
        this.maxDeltaPlus = maxDeltaPlus;
    }

    public WebQuotation getMaxDeltaMinus() {
        return maxDeltaMinus;
    }

    public void setMaxDeltaMinus(WebQuotation maxDeltaMinus) {
        this.maxDeltaMinus = maxDeltaMinus;
    }

    public WebQuotation getActualValue() {
        return actualValue;
    }

    public void setActualValue(WebQuotation actualValue) {
        this.actualValue = actualValue;
    }
}

