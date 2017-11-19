package com.infoshare.web.services.analyzer.analysis.investmentindicator;

import com.infoshare.mfinance.core.analyzer.indicator.InvestmentIndicator;
import com.infoshare.mfinance.core.models.analyses.criteria.IndicatorCriteria;
import com.infoshare.mfinance.core.models.analyses.results.IndicatorResult;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.web.services.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.infoshare.web.model.criterias.WebIndicatorCriteria;
import com.infoshare.web.services.analyzer.analysis.wrapper.AnalysisContent;
import com.infoshare.web.services.analyzer.analysis.wrapper.ComparisonContentWrapper;
import com.infoshare.web.services.bossa.IDataContainerService;
import com.infoshare.web.services.persistence.user.IUserService;
import com.infoshare.web.model.User;
import com.infoshare.web.services.reports.activity.IUserActivityService;
import com.infoshare.web.services.reports.activity.UserActivity;
import com.infoshare.web.utils.ConstantsProvider;
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
import java.util.List;

@WebServlet(urlPatterns = "/auth/userview/comparator")
public class IndicatorServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorServlet.class);
    private final String USER_ACTIVITY_ANALYSIS_SUBMMIT = "user subbmited: IndicatorComparator analyses";

    @Inject
    IDataContainerService container;

    @Inject
    IUserActivityService userActivityService;

    @Inject
    IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("../userview/comparator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String nameA = req.getParameter(ConstantsProvider.INVESTMENT_NAME_A);
            String nameB = req.getParameter(ConstantsProvider.INVESTMENT_NAME_B);
            boolean isFavouriteChecked = req.getParameter(ConstantsProvider.IS_FAVOURITE) != null;
            String userCustomName = req.getParameter(ConstantsProvider.USER_FAVOURITE_CUSTOM_NAME);

            List<String> names = new ArrayList<>();
            names.add(nameA);
            names.add(nameB);

            List<WebIndicatorCriteria> criteriaList = new ArrayList<>();
            ComparisonContentWrapper wrapper = new ComparisonContentWrapper();

            wrapper.setUserCustomName(userCustomName);

            for (String item : names) {
                WebIndicatorCriteria criteria = new WebIndicatorCriteria(
                        item, userCustomName, isFavouriteChecked);

                IndicatorResult result = new InvestmentIndicator(container.getDataContainer()
                        , new IndicatorCriteria(item)).getResult();


                criteriaList.add(criteria);

                wrapper.getAnanysisContentList().add(new AnalysisContent(criteria, result));
            }

            AnalysisComparisonContainer comparisonContainer =
                    new AnalysisComparisonContainer(isFavouriteChecked
                            , userCustomName, new ArrayList<>(criteriaList));

            User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
            user.getComparisonContainers().add(comparisonContainer);
            userService.update(user);
            userActivityService.saveActivity(new UserActivity(user.getLogin() , USER_ACTIVITY_ANALYSIS_SUBMMIT, req.getSession().getId()));

            req.setAttribute(ConstantsProvider.CONTENT_WRAPPER, wrapper);
            req.getRequestDispatcher("../userview/comparatorResult.jsp").forward(req, resp);

        } catch (NoDataForCriteria exception) {

            req.getRequestDispatcher("../userview/comparator.jsp").forward(req, resp);
            LOGGER.error("Failed to calculate Investment indicators.{}", exception.getMessage());
        }
    }
}