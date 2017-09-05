package com.infoshare.web.analyzer.analysis.wrapper;

import com.infoshare.core.analyzer.analyses.AnalysisCriteria;
import com.infoshare.core.analyzer.analyses.AnalysisResult;

public class AnalysisContent {

   private AnalysisCriteria criteria;
   private AnalysisResult result;

    public AnalysisContent(AnalysisCriteria criteria, AnalysisResult result) {
        this.criteria = criteria;
        this.result = result;
    }

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
