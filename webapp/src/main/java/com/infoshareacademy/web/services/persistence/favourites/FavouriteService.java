package com.infoshareacademy.web.services.persistence.favourites;

import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import java.util.List;

public interface FavouriteService {
     List<WebInvestmentRevenueCriteria> getAllRevenueCriteria();
}
