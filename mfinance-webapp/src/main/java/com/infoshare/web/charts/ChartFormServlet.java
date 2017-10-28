package com.infoshare.web.charts;

import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.mfinance.core.models.analyses.criteria.QuotationSeriesCriteria;
import com.infoshare.web.container.IModelContainerService;
import com.infoshare.web.user.User;
import com.infoshare.web.user.report.IUserActivityService;
import com.infoshare.web.user.report.UserActivity;
import com.infoshare.web.utils.ConstantsProvider;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/auth/userview/chart")
public class ChartFormServlet extends HttpServlet {

    private final String USER_ACTIVITY_ANALYSIS_SUBMMIT = "user subbmited: ChartComparison analyses";
    @Inject
    private IModelContainerService container;

    @Inject
    IUserActivityService userActivityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChartFormServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nameA = req.getParameter(ConstantsProvider.INVESTMENT_NAME_A);
            String nameB = req.getParameter(ConstantsProvider.INVESTMENT_NAME_B);
            String startDate = req.getParameter(ConstantsProvider.START_DATE);
            String endDate = req.getParameter(ConstantsProvider.END_DATE);


            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

            LocalDate startDATE = LocalDate.parse(startDate, formatter);
            LocalDate endDATE = LocalDate.parse(endDate, formatter);

            QuotationSeriesCriteria criteriaA = new QuotationSeriesCriteria(nameA, startDATE, endDATE);
            QuotationSeriesCriteria criteriaB = new QuotationSeriesCriteria(nameB, startDATE, endDATE);

            ChartGenerator chartGeneratorA = new ChartGenerator(container, criteriaA);
            JFreeChart chartA = chartGeneratorA.renderChart();
            ChartGenerator chartGeneratorB = new ChartGenerator(container, criteriaB);
            JFreeChart chartB = chartGeneratorB.renderChart();

            User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
            userActivityService.saveActivity(new UserActivity(user.getLogin() , USER_ACTIVITY_ANALYSIS_SUBMMIT, req.getSession().getId()));

            req.getSession().setAttribute("chartA", chartA);
            req.getSession().setAttribute("chartB", chartB);

            req.getRequestDispatcher("../userview/chartResult.jsp").forward(req, resp);
        } catch (NoDataForCriteria ex) {
            LOGGER.error("Failed to render chart.{}{}", ex.getMessage(), ex.getStackTrace());
        }
    }
}

