package com.infoshareacademy.web.services.persistence.favourites.strategies;

import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.services.persistence.user.UserService;

public interface FavouritesPersistence {
    void persist(WebAnalysisCriteria criteria, User user, UserService userService);
}
