package com.infoshare.web.adminpanel.email;

import com.infoshare.web.analyzer.IFavouriteService;

public class ReportComponents {

    private IFavouriteService favouriteService;

    public ReportComponents(IFavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    public IFavouriteService getFavouriteService() {
        return favouriteService;
    }
}
