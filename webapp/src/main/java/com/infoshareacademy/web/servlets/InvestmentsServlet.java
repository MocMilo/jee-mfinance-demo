package com.infoshareacademy.web.servlets;

import com.infoshareacademy.web.services.bossa.DataContainerService;
import com.infoshareacademy.web.utils.constants.ConstantsProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/investments")
public class InvestmentsServlet extends HttpServlet {
    @Inject
    private DataContainerService container;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ConstantsProvider.CURRENCY_COUNT, container.getDataContainer().getCurrenciesCount());
        req.setAttribute(ConstantsProvider.FUND_COUNT, container.getDataContainer().getFundsCount());
        req.setAttribute(ConstantsProvider.ALL_INVESTMENTS, container.getDataContainer().getInvestments());
        req.getRequestDispatcher("/investments.jsp").forward(req, resp);
    }
}
