package com.infoshare.web.analyzer.analysis.wrapper;

import com.infoshare.mfinance.core.models.analyses.criteria.AnalysisCriteria;
import com.infoshare.mfinance.core.models.analyses.results.AnalysisResult;
import com.infoshare.web.analyzer.analysis.model.PersistedAnalysisCriteria;

public class AnalysisContent {

   private PersistedAnalysisCriteria criteria;
   private AnalysisResult result;

    public AnalysisContent(PersistedAnalysisCriteria criteria, AnalysisResult result) {
        this.criteria = criteria;
        this.result = result;
    }

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
