package com.infoshare.web.services.persistence.favourites.strategies;

import com.infoshare.web.model.user.User;
import com.infoshare.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import com.infoshare.web.services.persistence.user.IUserService;


public class IVRFavouriteStrategy implements FavouritesPersistence {

    @Override
    public void persist(WebAnalysisCriteria criteria, User user, IUserService userService) {

        WebInvestmentRevenueCriteria ivrCriteria = (WebInvestmentRevenueCriteria) criteria;
        user.getFavourites().add(ivrCriteria);
        userService.update(user);
    }
}
