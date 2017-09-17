package com.infoshare.core.analyzer.analyses;


import com.infoshare.core.models.analyses.results.AnalysisResult;
import com.infoshare.core.models.exceptions.NoDataForCriteria;

public interface IResult {

   AnalysisResult getResult() throws NoDataForCriteria;


}
