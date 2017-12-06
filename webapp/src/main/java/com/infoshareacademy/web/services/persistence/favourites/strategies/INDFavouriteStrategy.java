package com.infoshareacademy.web.services.persistence.favourites.strategies;

import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebIndicatorCriteria;
import com.infoshareacademy.web.services.persistence.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class INDFavouriteStrategy implements FavouritesPersistence {
    private static final Logger LOGGER = LoggerFactory.getLogger(INDFavouriteStrategy.class);

    @Override
    public void persist(WebAnalysisCriteria criteria, User user, UserService userService) {
        WebIndicatorCriteria indCriteria = (WebIndicatorCriteria) criteria;
        user.getFavouritesIND().add(indCriteria);
        userService.update(user);
        LOGGER.info("Saved IND criteria: custom name:{}", criteria.getUserCustomName());
    }
}
