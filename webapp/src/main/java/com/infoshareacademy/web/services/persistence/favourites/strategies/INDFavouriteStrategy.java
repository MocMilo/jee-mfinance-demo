package com.infoshareacademy.web.services.persistence.favourites.strategies;

import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebIndicatorCriteria;
import com.infoshareacademy.web.services.persistence.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class INDFavouriteStrategy implements FavouritesPersistence {
    private static final Logger LOGGER = LoggerFactory.getLogger(INDFavouriteStrategy.class);

    @Override
    public void persist(WebAnalysisCriteria criteria, User user, IUserService userService) {
        WebIndicatorCriteria indCriteria = (WebIndicatorCriteria) criteria;
        user.getFavouritesIND().add(indCriteria);
        LOGGER.info("Save IND criteria: isFavourite:{} custom name:{}", criteria.isFavourite(), criteria.getUserCustomName());
        userService.update(user);
    }
}
