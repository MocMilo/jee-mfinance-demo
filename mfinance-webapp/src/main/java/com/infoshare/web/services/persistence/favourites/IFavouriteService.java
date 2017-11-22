package com.infoshare.web.services.persistence.favourites;

import com.infoshare.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;

import java.util.List;

public interface IFavouriteService {

    List<WebInvestmentRevenueCriteria> getAllFavouriteRevenueCriteria(long UserId);

    List<WebInvestmentRevenueCriteria> getAllRevenueCriteria();


}
