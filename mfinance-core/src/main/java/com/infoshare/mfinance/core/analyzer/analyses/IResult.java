package com.infoshare.mfinance.core.analyzer.analyses;


import com.infoshare.mfinance.core.models.analyses.results.AnalysisResult;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;

public interface IResult {

   AnalysisResult getResult() throws NoDataForCriteria;

}
