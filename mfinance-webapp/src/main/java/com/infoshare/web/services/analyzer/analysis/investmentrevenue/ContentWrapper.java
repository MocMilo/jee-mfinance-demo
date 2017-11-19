package com.infoshare.web.services.analyzer.analysis.investmentrevenue;


import com.infoshare.mfinance.core.models.analyses.results.AnalysisResult;
import com.infoshare.web.model.criterias.WebAnalysisCriteria;

public class ContentWrapper {

    private WebAnalysisCriteria criteria;
    private AnalysisResult result;

    public WebAnalysisCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(WebAnalysisCriteria criteria) {
        this.criteria = criteria;
    }

    public AnalysisResult getResult() {
        return result;
    }

    public void setResult(AnalysisResult result) {
        this.result = result;
    }

}
