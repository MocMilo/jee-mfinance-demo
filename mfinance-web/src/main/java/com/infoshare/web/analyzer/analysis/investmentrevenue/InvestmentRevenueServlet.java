package com.infoshare.web.analyzer.analysis.investmentrevenue;

import com.infoshare.web.analyzer.IFavouriteService;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.mfinance.core.analyzer.analyses.revenue.InvestmentRevenue;
import com.infoshare.mfinance.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.mfinance.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.web.container.IModelContainerService;
import com.infoshare.web.user.IUserService;
import com.infoshare.web.user.User;
import com.infoshare.web.user.report.IUserActivityService;
import com.infoshare.web.user.report.UserActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import static com.infoshare.web.utils.ConstantsProvider.DATE_PATTERN;
import static com.infoshare.web.utils.ConstantsProvider.INVESTMENT_NAME;
import static com.infoshare.web.utils.ConstantsProvider.CAPITAL;
import static com.infoshare.web.utils.ConstantsProvider.BUY_DATE;
import static com.infoshare.web.utils.ConstantsProvider.SELL_DATE;
import static com.infoshare.web.utils.ConstantsProvider.USER_FAVOURITE_CUSTOM_NAME;
import static com.infoshare.web.utils.ConstantsProvider.IS_FAVOURITE;
import static com.infoshare.web.utils.ConstantsProvider.CONTENT_WRAPPER;
import static com.infoshare.web.utils.ConstantsProvider.APP_MESSAGE;
import static com.infoshare.web.utils.ConstantsProvider.NO_DATA_FOR_CRITERIA_MESSAGE;
import static com.infoshare.web.utils.ConstantsProvider.CRITERIA_MODERATION_MESSAGE;
import static com.infoshare.web.utils.ConstantsProvider.AUTH_USER;

@WebServlet(urlPatterns = "/auth/userview/investmentrevenue")
public class InvestmentRevenueServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentRevenueServlet.class);
    private final String USER_ACTIVITY_ANALYSIS_SUBMMIT = "user subbmited: InvestmentRevenue analyses";
    private ContentWrapper wrapper = new ContentWrapper();

    @Inject
    private IModelContainerService container;
    @Inject
    private IFavouriteService favouriteService;
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            String investmentName = req.getParameter(INVESTMENT_NAME);
            String capital = req.getParameter(CAPITAL);
            String buyDate = req.getParameter(BUY_DATE);
            String sellDate = req.getParameter(SELL_DATE);
            String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);
            Boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;

            CriteriaForm form = new CriteriaForm();
            form.setInvestmentName(investmentName);
            form.setCapital(capital);
            form.setBuyDate(buyDate);
            form.setSellDate(sellDate);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<CriteriaForm>> violations = validator.validate(form);

            if(!violations.isEmpty()){
                req.setAttribute(INVESTMENT_NAME, investmentName);
                req.setAttribute(CAPITAL, capital);
                req.setAttribute(BUY_DATE, buyDate);
                req.setAttribute(SELL_DATE, sellDate);
                req.setAttribute("violations", violations);
                req.getRequestDispatcher("../userview/investmentRevenue.jsp").forward(req, resp);
                return;
            }

            BigDecimal investmentCapital = new BigDecimal(capital);
            LocalDate investmentBuyDate = LocalDate.parse(buyDate, formatter);
            LocalDate investmentSellDate = LocalDate.parse(sellDate, formatter);

            InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria(investmentCapital
                    , investmentBuyDate
                    , investmentSellDate
                    , investmentName
                    , isFavouriteChecked);

            InvestmentRevenueResult result = (new InvestmentRevenue(container.getMainContainer(), criteria))
                    .getResult();

            User dbUser = userService.get(((User) req.getSession()
                    .getAttribute(AUTH_USER)).getId());

            dbUser.getFavourites().add(new PersistedInvestmentRevenueCriteria()
                    .getCriteria(criteria, userCustomName));
            userService.update(dbUser);
            userActivityService.saveActivity(new UserActivity(dbUser.getLogin() , USER_ACTIVITY_ANALYSIS_SUBMMIT, req.getSession().getId()));

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
        wrapper.setCriteria(criteria);
        wrapper.setResult(result);
        if (result.getFinallyEvaluatedInput().getModifiedBySuggester()) {
            wrapper.setMessage(CRITERIA_MODERATION_MESSAGE);
        }
        return wrapper;
    }
}
