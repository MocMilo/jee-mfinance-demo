package com.infoshare.web.analyzer.analysis.investmentrevenue;

import com.infoshare.core.models.analyses.criteria.AnalysisCriteria;
import com.infoshare.core.models.analyses.results.AnalysisResult;

public class ContentWrapper {

    private AnalysisCriteria criteria;
    private AnalysisResult result;
    private String message;

    public AnalysisCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(AnalysisCriteria criteria) {
        this.criteria = criteria;
    }

    public AnalysisResult getResult() {
        return result;
    }

    public void setResult(AnalysisResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
