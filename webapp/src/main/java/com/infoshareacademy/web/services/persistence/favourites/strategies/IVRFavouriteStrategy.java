package com.infoshareacademy.web.services.persistence.favourites.strategies;

import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import com.infoshareacademy.web.services.persistence.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IVRFavouriteStrategy implements FavouritesPersistence {
    private static final Logger LOGGER = LoggerFactory.getLogger(IVRFavouriteStrategy.class);

    @Override
    public void persist(WebAnalysisCriteria criteria, User user, UserService userService) {
        WebInvestmentRevenueCriteria ivrCriteria = (WebInvestmentRevenueCriteria) criteria;
        user.getFavouritesIVR().add(ivrCriteria);
        userService.update(user);
        LOGGER.info("Saved IVR criteria: custom name:{}", criteria.getUserCustomName());
    }
}
