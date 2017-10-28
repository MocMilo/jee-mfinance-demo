package com.infoshare.web.analyzer.analysis.investmentrevenue;

import com.infoshare.mfinance.core.models.analyses.criteria.AnalysisCriteria;
import com.infoshare.mfinance.core.models.analyses.results.AnalysisResult;

public class ContentWrapper {

    private AnalysisCriteria criteria;
    private AnalysisResult result;

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


}
