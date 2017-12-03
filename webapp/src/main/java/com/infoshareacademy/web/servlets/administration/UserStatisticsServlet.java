package com.infoshareacademy.web.servlets.administration;

import com.infoshareacademy.web.services.persistence.favourites.FavouriteService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.ALL_INV_REV_CRIT;

@WebServlet(urlPatterns = "/admin/statistics")
public class UserStatisticsServlet extends HttpServlet {
    @Inject
    private FavouriteService favouriteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ALL_INV_REV_CRIT, favouriteService.getAllRevenueCriteria());
        req.getRequestDispatcher("/admin/statistics.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ALL_INV_REV_CRIT, favouriteService.getAllRevenueCriteria());
        req.getRequestDispatcher("/admin/statistics.jsp").forward(req, resp);
    }
}



