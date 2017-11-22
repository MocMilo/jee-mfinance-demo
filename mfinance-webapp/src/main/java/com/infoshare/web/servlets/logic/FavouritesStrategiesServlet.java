package com.infoshare.web.servlets.logic;


import com.infoshare.web.model.user.User;
import com.infoshare.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshare.web.services.persistence.favourites.strategies.INDFavouriteStrategy;
import com.infoshare.web.services.persistence.user.IUserService;
import com.infoshare.web.services.persistence.favourites.strategies.FavouritesPersistence;
import com.infoshare.web.services.persistence.favourites.strategies.IVRFavouriteStrategy;
import com.infoshare.web.utils.constants.ConstantsProvider;
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

@WebServlet(urlPatterns = "/auth/userview/favourites")
public class FavouritesStrategiesServlet extends HttpServlet {


    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzerStrategiesServlet.class);
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

        this.persist(webAnalysisCriteria);

        req.setAttribute("criteria", webAnalysisCriteria);
        req.getRequestDispatcher("/auth/userview/analysis").forward(req, resp);

    }

    private void persist(WebAnalysisCriteria webAnalysisCriteria) {

        String strategy = webAnalysisCriteria.getStrategy();
        persistenceStrategies.get(strategy).persist(webAnalysisCriteria, user, userService);
    }
}
