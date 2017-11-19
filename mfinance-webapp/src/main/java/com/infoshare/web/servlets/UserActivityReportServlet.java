package com.infoshare.web.servlets;

import com.infoshare.web.services.reports.activity.IUserActivityService;
import com.infoshare.web.services.reports.report.CSVGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/auth/adminview/userstatistics/csv")
public class UserActivityReportServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserActivityReportServlet.class);

    @EJB
    private CSVGenerator csvGenerator;

    @Inject
    IUserActivityService userActivityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        for(String line : csvGenerator.generateCSVLines()){
            resp.getWriter().write(line);
        }

        resp.setContentType("text/csv");
        resp.setHeader("Content-Disposition", "attachment; filename=useractivity.csv");
    }

}