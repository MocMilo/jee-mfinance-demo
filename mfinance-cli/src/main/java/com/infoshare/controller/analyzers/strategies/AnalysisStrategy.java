package com.infoshare.controller.analyzers.strategies;

import com.infoshare.core.models.exceptions.NoDataForCriteria;

public interface AnalysisStrategy  {

  void doAnalyzeAlgorithm(String[] args) throws NoDataForCriteria;

}
