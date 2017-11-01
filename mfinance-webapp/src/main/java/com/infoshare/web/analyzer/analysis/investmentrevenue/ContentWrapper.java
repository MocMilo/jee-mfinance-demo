package com.infoshare.web.analyzer.analysis.investmentrevenue;


import com.infoshare.mfinance.core.models.analyses.results.AnalysisResult;
import com.infoshare.web.analyzer.analysis.model.PersistedAnalysisCriteria;

public class ContentWrapper {

    private PersistedAnalysisCriteria criteria;
    private AnalysisResult result;

    public PersistedAnalysisCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(PersistedAnalysisCriteria criteria) {
        this.criteria = criteria;
    }

    public AnalysisResult getResult() {
        return result;
    }

    public void setResult(AnalysisResult result) {
        this.result = result;
    }

}
