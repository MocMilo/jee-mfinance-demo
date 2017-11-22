package com.infoshare.web.servlets;


import com.infoshare.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.analyzer.results.WebAnalysisResult;
import com.infoshare.web.model.user.User;
import com.infoshare.web.services.analyzer.AnalysisStrategy;
import com.infoshare.web.services.analyzer.INDAnalysisStrategy;
import com.infoshare.web.services.analyzer.IVRAnalysisStrategy;
import com.infoshare.web.services.bossa.IDataContainerService;
import com.infoshare.web.utils.constants.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;
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


@WebServlet(urlPatterns = "/auth/userview/userfavourites")
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

        favouritesCriterias = new ArrayList<>();
        favouritesResults = new ArrayList<>();

        User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);

        favouritesCriterias.addAll(user.getFavourites());
        favouritesCriterias.addAll(user.getFavouriteInvestmentIndicators());

        LOGGER.info("Number of user favourites criterias:{}", favouritesCriterias.size());

        for (WebAnalysisCriteria item : favouritesCriterias) {
            favouritesResults.add(this.getResult(item));
        }

        req.setAttribute("analysisResults", favouritesResults);
        req.getRequestDispatcher("../userview/userFavourites.jsp").forward(req, resp);
    }

    private WebAnalysisResult getResult(WebAnalysisCriteria criteria) {

        String strategy = criteria.getStrategy();

        return analysisStrategies.get(strategy)
                .getResult(criteria, container);
    }
}

