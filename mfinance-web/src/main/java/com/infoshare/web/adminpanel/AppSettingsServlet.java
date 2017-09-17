package com.infoshare.web.adminpanel;

import com.infoshare.web.container.IModelContainerService;

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

@WebServlet(urlPatterns = "/auth/adminview/appsettings")
public class AppSettingsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingsServlet.class);

    @Inject
    IModelContainerService container;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(ConstantsProvider.CURRENCY_COUNT, container.getMainContainer().getCurrenciesCount());
        req.setAttribute(ConstantsProvider.FUND_COUNT, container.getMainContainer().getFundsCount());
        req.getRequestDispatcher("../adminview/appSettings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            LOGGER.info("Administrator Main Container manual reloading initialized...");

            container.reload();
            req.setAttribute(ConstantsProvider.CURRENCY_COUNT, container.getMainContainer().getCurrenciesCount());
            req.setAttribute(ConstantsProvider.FUND_COUNT, container.getMainContainer().getFundsCount());

            LOGGER.info("Main Container reloaded!Currencies items:{} Funds items:{}",
                    container.getMainContainer().getCurrenciesCount(),
                    container.getMainContainer().getFundsCount());

        } catch (RuntimeException ex) {

            LOGGER.error("FATAL ERROR: Failed to manually reload data models CSV files! {}", ex.getMessage());
        }
        req.getRequestDispatcher("../adminview/appSettings.jsp").forward(req, resp);
    }
}
