package com.infoshare.core.analyzer.analyses;


import com.infoshare.core.analyzer.analyses.exception.NoDataForCriteria;

public interface IResult {

   AnalysisResult getResult() throws NoDataForCriteria;


}
