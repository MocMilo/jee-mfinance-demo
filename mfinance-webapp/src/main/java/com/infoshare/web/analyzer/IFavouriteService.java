package com.infoshare.web.analyzer;

import com.infoshare.web.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.infoshare.web.analyzer.analysis.model.PersistedInvestmentRevenueCriteria;

import java.util.List;

public interface IFavouriteService {

    List<PersistedInvestmentRevenueCriteria> getAllFavouriteRevenueCriteria(long UserId);

    List<PersistedInvestmentRevenueCriteria> getAllRevenueCriteria();

    List<AnalysisComparisonContainer> getAllUserFavouriteAnalysisContainers(long UserId);
}
