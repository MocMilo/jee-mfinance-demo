package com.infoshare.web.servlets.charts;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

    @WebServlet(urlPatterns = "/auth/userview/chartA")
    public class ChartAServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            JFreeChart chart = (JFreeChart) req.getSession().getAttribute("chartA");
            OutputStream out = resp.getOutputStream();
            resp.setContentType("image/png");

            ChartUtilities.writeChartAsPNG(out, chart, 800, 300);
        }
}
