package com.infoshareacademy.web.services.analyzer;

import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.results.WebAnalysisResult;
import com.infoshareacademy.web.services.bossa.DataContainerService;

public interface AnalysisStrategy {
    WebAnalysisResult getResult(WebAnalysisCriteria criteria, DataContainerService dataContainer);
}
