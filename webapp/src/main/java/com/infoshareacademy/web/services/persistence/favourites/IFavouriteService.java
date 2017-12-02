package com.infoshareacademy.web.services.persistence.favourites;

import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import java.util.List;

public interface IFavouriteService {
    List<WebInvestmentRevenueCriteria> getAllFavouriteRevenueCriteria(long UserId);

    List<WebInvestmentRevenueCriteria> getAllRevenueCriteria();
}
