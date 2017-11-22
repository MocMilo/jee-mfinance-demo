package com.infoshare.web.services.analyzer;


import com.infoshare.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.analyzer.results.WebAnalysisResult;
import com.infoshare.web.services.bossa.IDataContainerService;

public interface AnalysisStrategy {

WebAnalysisResult getResult(WebAnalysisCriteria criteria, IDataContainerService dataContainer);
}
