package com.infoshare.web.services.persistence.favourites.strategies;

import com.infoshare.web.model.user.User;
import com.infoshare.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshare.web.services.persistence.user.IUserService;

public interface FavouritesPersistence {

    void persist(WebAnalysisCriteria criteria, User user, IUserService userService);

}
