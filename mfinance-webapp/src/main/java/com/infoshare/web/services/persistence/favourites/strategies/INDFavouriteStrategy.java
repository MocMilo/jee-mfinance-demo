package com.infoshare.web.services.persistence.favourites.strategies;

import com.infoshare.web.model.user.User;
import com.infoshare.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.analyzer.criterias.WebIndicatorCriteria;
import com.infoshare.web.services.persistence.user.IUserService;

public class INDFavouriteStrategy implements FavouritesPersistence {

    @Override
    public void persist(WebAnalysisCriteria criteria, User user, IUserService userService) {

        WebIndicatorCriteria indCriteria = (WebIndicatorCriteria) criteria;
        user.getFavouriteInvestmentIndicators().add(indCriteria);
        userService.update(user);
    }
}
