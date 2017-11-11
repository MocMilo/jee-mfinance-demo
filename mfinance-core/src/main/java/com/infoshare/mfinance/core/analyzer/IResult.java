package com.infoshare.mfinance.core.analyzer;


import com.infoshare.mfinance.core.models.analyses.results.AnalysisResult;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;

public interface IResult {

   AnalysisResult getResult() throws NoDataForCriteria;

}
