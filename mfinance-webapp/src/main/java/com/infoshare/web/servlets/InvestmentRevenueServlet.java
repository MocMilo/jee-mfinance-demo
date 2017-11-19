package com.infoshare.web.servlets;

import com.infoshare.mfinance.core.analyzer.revenue.InvestmentRevenue;
import com.infoshare.mfinance.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.mfinance.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.web.services.analyzer.analysis.investmentrevenue.ContentWrapper;
import com.infoshare.web.model.criterias.WebInvestmentRevenueCriteria;
import com.infoshare.web.services.bossa.IDataContainerService;
import com.infoshare.web.services.persistence.user.IUserService;
import com.infoshare.web.model.User;
import com.infoshare.web.services.reports.activity.IUserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.infoshare.web.utils.ConstantsProvider.*;

@WebServlet(urlPatterns = "/auth/userview/investmentrevenue")
public class InvestmentRevenueServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentRevenueServlet.class);
    private final String USER_ACTIVITY_ANALYSIS_SUBMMIT = "user subbmited: InvestmentRevenue analyses";
    private ContentWrapper wrapper = new ContentWrapper();

    @Inject
    private IDataContainerService container;
    @Inject
    private IUserService userService;
    @Inject
    private IUserActivityService userActivityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../userview/investmentRevenue.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            InvestmentRevenueCriteria criteria = (InvestmentRevenueCriteria ) req.getAttribute("criteria");

           /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            String investmentName = req.getParameter(INVESTMENT_NAME);
            String capital = req.getParameter(CAPITAL);
            String buyDate = req.getParameter(BUY_DATE);
            String sellDate = req.getParameter(SELL_DATE);*/
            String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);
            Boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;

            InvestmentRevenueResult result = (new InvestmentRevenue(container.getDataContainer(), criteria))
                    .getResult();

            User dbUser = userService.get(((User) req.getSession()
                    .getAttribute(AUTH_USER)).getId());
/*

            dbUser.getFavourites().add(new WebInvestmentRevenueCriteria()
                    .getCriteria(criteria, userCustomName, isFavouriteChecked));

            userService.update(dbUser);
            userActivityService.saveActivity(new UserActivity(dbUser.getLogin() , USER_ACTIVITY_ANALYSIS_SUBMMIT, req.getSession().getId()));
*/

            req.setAttribute(CONTENT_WRAPPER, this.getContent(criteria, result));
            req.getRequestDispatcher("../userview/investmentRevenueResult.jsp").forward(req, resp);

            LOGGER.info("Criteria Submitted by user Id:{}, login:{}", dbUser.getId(), dbUser.getLogin());

        } catch (NoDataForCriteria ex) {
            req.setAttribute(APP_MESSAGE, NO_DATA_FOR_CRITERIA_MESSAGE);
            req.getRequestDispatcher("../userview/investmentRevenue.jsp").forward(req, resp);
            LOGGER.warn(ex.getMessage());
        }
    }

    private ContentWrapper getContent(InvestmentRevenueCriteria criteria, InvestmentRevenueResult result) {

        //TODO impl converter
        WebInvestmentRevenueCriteria persistedInvestmentRevenueCriteria = new WebInvestmentRevenueCriteria();
        persistedInvestmentRevenueCriteria.setInvestmentName(criteria.getInvestmentName());
        persistedInvestmentRevenueCriteria.setInvestedCapital(criteria.getInvestedCapital());
        persistedInvestmentRevenueCriteria.setBuyDate(criteria.getBuyDate());
        persistedInvestmentRevenueCriteria.setSellDate(criteria.getSellDate());

        wrapper.setCriteria(persistedInvestmentRevenueCriteria);
        wrapper.setResult(result);
        return wrapper;
    }
}
