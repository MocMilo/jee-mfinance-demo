package com.infoshareacademy.mfinance.core.analyzer;

import com.infoshareacademy.mfinance.core.models.analyzer.results.AnalysisResult;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;

public interface Result {
   AnalysisResult getResult() throws NoDataForCriteria;
}
