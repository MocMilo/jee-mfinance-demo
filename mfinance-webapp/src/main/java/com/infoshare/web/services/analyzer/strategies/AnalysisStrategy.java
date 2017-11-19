package com.infoshare.web.services.analyzer.strategies;


import com.infoshare.web.model.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.results.WebAnalysisResult;
import com.infoshare.web.services.bossa.IDataContainerService;

public interface AnalysisStrategy {

/*  WebAnalysisResult getResult(ParserResult result, DataContainer bossa);*/

WebAnalysisResult getResult(WebAnalysisCriteria criteria, IDataContainerService dataContainer);

}
