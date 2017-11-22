package com.infoshare.web.servlets.logic;

import com.infoshare.web.services.analyzer.AnalysisStrategy;
import com.infoshare.web.services.analyzer.INDAnalysisStrategy;
import com.infoshare.web.services.analyzer.IVRAnalysisStrategy;
import com.infoshare.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.analyzer.results.WebAnalysisResult;
import com.infoshare.web.services.bossa.IDataContainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = "/auth/userview/analysis")
public class AnalyzerStrategiesServlet extends HttpServlet {


    @Inject
    private IDataContainerService dataContainer;

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzerStrategiesServlet.class);
    private static Map<String, AnalysisStrategy> analysisStrategies = new HashMap<>();

    static {
        analysisStrategies.put("IVR", new IVRAnalysisStrategy());
        analysisStrategies.put("IND", new INDAnalysisStrategy());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebAnalysisCriteria criteria = (WebAnalysisCriteria) req.getAttribute("criteria");
        LOGGER.info("req getAttribute criteria strategy:" + criteria.getStrategy());

        WebAnalysisResult webAnalysisResult = this.getResult(criteria);
        LOGGER.info("req setAttribute result strategy:"  +  webAnalysisResult.getStrategy());

        req.setAttribute("analysisResult", webAnalysisResult);
        req.getRequestDispatcher("../userview/analysisResult.jsp").forward(req, resp);
    }

    private WebAnalysisResult getResult(WebAnalysisCriteria criteria) {

        String analysisName = criteria.getStrategy();

        return analysisStrategies.get(analysisName)
                .getResult(criteria, dataContainer);
    }
}
