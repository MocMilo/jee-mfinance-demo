package com.infoshare.web.services.administration.email;

import com.infoshare.web.services.persistence.favourites.IFavouriteService;

public class ReportComponents {

    private IFavouriteService favouriteService;

    public ReportComponents(IFavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    public IFavouriteService getFavouriteService() {
        return favouriteService;
    }
}
