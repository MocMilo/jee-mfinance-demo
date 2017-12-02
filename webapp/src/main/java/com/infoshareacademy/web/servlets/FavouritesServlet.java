package com.infoshareacademy.web.servlets;

import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.results.WebAnalysisResult;
import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.services.analyzer.AnalysisStrategy;
import com.infoshareacademy.web.services.analyzer.INDAnalysisStrategy;
import com.infoshareacademy.web.services.analyzer.IVRAnalysisStrategy;
import com.infoshareacademy.web.services.bossa.IDataContainerService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/favourites")
public class FavouritesServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FavouritesServlet.class);
    private static Map<String, AnalysisStrategy> analysisStrategies = new HashMap<>();

    static {
        analysisStrategies.put("IVR", new IVRAnalysisStrategy());
        analysisStrategies.put("IND", new INDAnalysisStrategy());
    }

    @Inject
    private IDataContainerService container;

    private List<WebAnalysisCriteria> favouritesCriterias;
    private List<WebAnalysisResult> favouritesResults;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
        favouritesCriterias = new ArrayList<>();
        favouritesResults = new ArrayList<>();
        favouritesCriterias.addAll(user.getFavouritesIVR());
        favouritesCriterias.addAll(user.getFavouritesIND());

        LOGGER.info("Number of user favourites criterias:{}", favouritesCriterias.size());

        for (WebAnalysisCriteria item : favouritesCriterias) {
            favouritesResults.add(this.getResult(item));
        }
        req.setAttribute("analysisResults", favouritesResults);
        req.getRequestDispatcher("/favourites.jsp").forward(req, resp);
    }

    private WebAnalysisResult getResult(WebAnalysisCriteria criteria) {
        String strategy = criteria.getStrategy();
        return analysisStrategies.get(strategy)
                .getResult(criteria, container);
    }
}

