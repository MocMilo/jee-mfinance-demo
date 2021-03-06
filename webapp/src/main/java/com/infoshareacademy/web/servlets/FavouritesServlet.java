package com.infoshareacademy.web.servlets;

import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.results.WebAnalysisResult;
import com.infoshareacademy.web.model.session.SessionContainer;
import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.services.analyzer.AnalysisStrategy;
import com.infoshareacademy.web.services.analyzer.INDAnalysisStrategy;
import com.infoshareacademy.web.services.analyzer.IVRAnalysisStrategy;
import com.infoshareacademy.web.services.bossa.DataContainerService;
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
    private SessionContainer sessionContainer;
    @Inject
    private DataContainerService container;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = sessionContainer.getUser();
        List<WebAnalysisCriteria> favouritesCriteriaList = new ArrayList<>();
        List<WebAnalysisResult> favouritesResults = new ArrayList<>();
        favouritesCriteriaList.addAll(user.getFavouritesIVR());
        favouritesCriteriaList.addAll(user.getFavouritesIND());
        LOGGER.info("Number of user favourites criteria:{}", favouritesCriteriaList.size());
        for (WebAnalysisCriteria item : favouritesCriteriaList) {
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

