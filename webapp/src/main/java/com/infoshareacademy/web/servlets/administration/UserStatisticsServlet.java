package com.infoshareacademy.web.servlets.administration;

import com.infoshareacademy.web.services.persistence.favourites.IFavouriteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by milo on 08.06.17.
 */

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.ALL_INV_REV_CRIT;

@WebServlet(urlPatterns = "/auth/adminview/userstatistics")
public class UserStatisticsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatisticsServlet.class);

    @Inject
    IFavouriteService favouriteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(ALL_INV_REV_CRIT , favouriteService.getAllRevenueCriteria());
        req.getRequestDispatcher("../adminview/userStatistics.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(ALL_INV_REV_CRIT , favouriteService.getAllRevenueCriteria());
        req.getRequestDispatcher("../adminview/userStatistics.jsp").forward(req, resp);
    }
}



