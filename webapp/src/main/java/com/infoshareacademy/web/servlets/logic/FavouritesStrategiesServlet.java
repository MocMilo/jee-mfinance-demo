package com.infoshareacademy.web.servlets.logic;


import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.services.persistence.favourites.strategies.INDFavouriteStrategy;
import com.infoshareacademy.web.services.persistence.user.IUserService;
import com.infoshareacademy.web.services.persistence.favourites.strategies.FavouritesPersistence;
import com.infoshareacademy.web.services.persistence.favourites.strategies.IVRFavouriteStrategy;
import com.infoshareacademy.web.utils.constants.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/favourite")
public class FavouritesStrategiesServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FavouritesStrategiesServlet.class);
    private static Map<String, FavouritesPersistence> persistenceStrategies = new HashMap<>();

    private User user;

    @Inject
    private IUserService userService;

    static {
        persistenceStrategies.put("IVR", new IVRFavouriteStrategy());
        persistenceStrategies.put("IND", new INDFavouriteStrategy());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebAnalysisCriteria webAnalysisCriteria = (WebAnalysisCriteria) req.getAttribute("criteria");
        user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);

        if (webAnalysisCriteria.isFavourite()) {
            this.persist(webAnalysisCriteria);
        }

        LOGGER.info("Redirect to analyzer servlet. isFavourite{}, strategy:{}, userCustomName:{}",
                webAnalysisCriteria.isFavourite(),
                webAnalysisCriteria.getStrategy(),
                webAnalysisCriteria.getUserCustomName());

        req.setAttribute("criteria", webAnalysisCriteria);
        req.getRequestDispatcher("/analysis").forward(req, resp);
    }

    private void persist(WebAnalysisCriteria webAnalysisCriteria) {

        LOGGER.info("Criteria to persist: isFavourite:{} strategy:{} custom name:{}",
                webAnalysisCriteria.isFavourite(),
                webAnalysisCriteria.getStrategy(),
                webAnalysisCriteria.getUserCustomName());

        String strategy = webAnalysisCriteria.getStrategy();
        persistenceStrategies.get(strategy).persist(webAnalysisCriteria, user, userService);
    }
}
