package com.infoshare.web.services.persistence.favourites;

import com.infoshare.mfinance.core.analyzer.revenue.InvestmentRevenue;
import com.infoshare.mfinance.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.web.services.analyzer.analysis.investmentrevenue.ContentWrapper;
import com.infoshare.web.utils.converters.InvestmentRevenueCriteriaConverter;
import com.infoshare.web.model.criterias.WebInvestmentRevenueCriteria;
import com.infoshare.web.services.bossa.IDataContainerService;
import com.infoshare.web.services.persistence.user.IUserService;
import com.infoshare.web.model.User;
import com.infoshare.web.services.reports.activity.IUserActivityService;
import com.infoshare.web.services.reports.activity.UserActivity;
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
import java.util.LinkedHashSet;
import java.util.List;

import static com.infoshare.web.utils.ConstantsProvider.*;

@WebServlet(urlPatterns = "/auth/userview/favouriterevenue")
public class FavouriteRevenueServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FavouriteRevenueServlet.class);
    private final String USER_ACTIVITY_FAVOURITES_DISPLAY = "user displayed Favourites: InvestmentRevenue analyses.";
    private InvestmentRevenueCriteriaConverter converter = new InvestmentRevenueCriteriaConverter();

    @Inject
    IDataContainerService container;
    @Inject
    IUserService userService;
    @Inject
    IFavouriteService favouriteService;
    @Inject
    IUserActivityService userActivityService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(AUTH_USER);

        List<WebInvestmentRevenueCriteria> criteriaList = favouriteService
                .getAllFavouriteRevenueCriteria(user.getId());

        userActivityService.saveActivity(new UserActivity(user.getLogin(),
                USER_ACTIVITY_FAVOURITES_DISPLAY,
                req.getSession().getId()));

        req.setAttribute(CONTENT_WRAPPER_COLLECTION, this.getAllWrapperedContent(criteriaList));
        req.getRequestDispatcher("../userview/favouriteRevenue.jsp").forward(req, resp);

    }

    private List<ContentWrapper> getAllWrapperedContent(List<WebInvestmentRevenueCriteria> criteriaList) {

        List<ContentWrapper> contentWrappers = new ArrayList<>();
        try {
            for (WebInvestmentRevenueCriteria criteria : criteriaList) {
/*                InvestmentRevenueResult result = (new InvestmentRevenue(bossa.getDataContainer(),
                        criteria.getConvertedCriteria(criteria))).getResult();*/

                InvestmentRevenueResult result = (new InvestmentRevenue(container.getDataContainer(),
                        converter.convertFrom(criteria))).getResult();

                ContentWrapper revenueWrapper = getContent(criteria, result);
                contentWrappers.add(revenueWrapper);

             /* fixme
             LOGGER.info(result.getCapitalRevenueDeltaPrecentValue().toString());
             */
                LOGGER.info(result.getCapitalRevenueValue().toString());
            }

        } catch (NoDataForCriteria ex) {
            LOGGER.error("Content Wrapper failure: {}", ex.getMessage());
        }
        return contentWrappers;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String criteriaId = req.getParameter(CRITERIA_ID);
        String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);

        User dbUser = userService.get(((User) req.getSession()
                .getAttribute(AUTH_USER)).getId());

        List<WebInvestmentRevenueCriteria> criteriaList = new ArrayList<>(dbUser.getFavourites());

        if (criteriaList != null && !criteriaList.isEmpty()) {
            LOGGER.info("Updating Criteria, current user criteria list size:{}, user Id:{}, login:{}",
                    criteriaList.size(), dbUser.getId(), dbUser.getLogin());

            int i = getCriteriaArrayListId(criteriaList, criteriaId);

            if (req.getParameter(UPDATE_ACTION) != null) {

                criteriaList.get(i).setUserCustomName(userCustomName);
                LOGGER.info("Analysis user custom name changed (updateAction) user Id:{}, login:{}",
                        dbUser.getId(), dbUser.getLogin());

            } else if (req.getParameter(DELETE_ACTION) != null) {

                criteriaList.get(i).setFavourite(false);
                LOGGER.info("Analysis removed from favourites (deleteAction) user Id:{}, login:{}",
                        dbUser.getId(), dbUser.getLogin());
            }
            dbUser.setFavourites(new LinkedHashSet<>(criteriaList));
            userService.update(dbUser);
            LOGGER.info("Criteria upadted. User Id:{}, login:{}", dbUser.getId(), dbUser.getLogin());
        }

        resp.sendRedirect("../userview/favouriterevenue");
    }

    private int getCriteriaArrayListId(List<WebInvestmentRevenueCriteria> criteria, String criteriaId) {
        int i = 0;
        for (WebInvestmentRevenueCriteria c : criteria) {
            if (c.getId() == Long.parseLong(criteriaId)) {
                LOGGER.info("criteria array Id:{}", i);
                break;
            }
            i++;
        }
        return i;
    }

    private ContentWrapper getContent(WebInvestmentRevenueCriteria criteria, InvestmentRevenueResult result) {
        ContentWrapper wrapper = new ContentWrapper();

        wrapper.setCriteria(criteria);
        wrapper.setResult(result);
        return wrapper;
    }
}
