package com.infoshare.web.services.persistence.favourites;

import com.infoshare.web.services.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.infoshare.web.model.criterias.WebInvestmentRevenueCriteria;

import java.util.List;

public interface IFavouriteService {

    List<WebInvestmentRevenueCriteria> getAllFavouriteRevenueCriteria(long UserId);

    List<WebInvestmentRevenueCriteria> getAllRevenueCriteria();

    List<AnalysisComparisonContainer> getAllUserFavouriteAnalysisContainers(long UserId);
}
