package com.infoshare.web.analyzer;

import com.infoshare.web.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.infoshare.web.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public interface IFavouriteService {

    List<PersistedInvestmentRevenueCriteria> getAllFavouriteRevenueCriteria(long UserId);

    List<PersistedInvestmentRevenueCriteria> getAllRevenueCriteria();

    List<AnalysisComparisonContainer> getAllUserFavouriteAnalysisContainers(long UserId);
}
