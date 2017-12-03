package com.infoshareacademy.web.servlets.administration;

import com.infoshareacademy.web.services.bossa.DataContainerService;
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

@WebServlet(urlPatterns = "/admin/settings")
public class AppSettingsServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingsServlet.class);

    @Inject
    private DataContainerService container;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ConstantsProvider.CURRENCY_COUNT, container.getDataContainer().getCurrenciesCount());
        req.setAttribute(ConstantsProvider.FUND_COUNT, container.getDataContainer().getFundsCount());
        req.getRequestDispatcher("/admin/settings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Administrator Main Container manual reloading initialized...");
        container.reload();
        req.setAttribute(ConstantsProvider.CURRENCY_COUNT, container.getDataContainer().getCurrenciesCount());
        req.setAttribute(ConstantsProvider.FUND_COUNT, container.getDataContainer().getFundsCount());

        LOGGER.info("Main Container reloaded!Currencies items:{} Funds items:{}",
                container.getDataContainer().getCurrenciesCount(),
                container.getDataContainer().getFundsCount());

        req.getRequestDispatcher("/admin/settings.jsp").forward(req, resp);
    }
}
