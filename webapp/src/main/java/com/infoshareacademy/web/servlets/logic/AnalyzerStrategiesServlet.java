package com.infoshareacademy.web.servlets.logic;

import com.infoshareacademy.web.services.analyzer.AnalysisStrategy;
import com.infoshareacademy.web.services.analyzer.INDAnalysisStrategy;
import com.infoshareacademy.web.services.analyzer.IVRAnalysisStrategy;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.results.WebAnalysisResult;
import com.infoshareacademy.web.services.bossa.DataContainerService;
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

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.*;

@WebServlet(urlPatterns = "/analysis")
public class AnalyzerStrategiesServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzerStrategiesServlet.class);
    private static Map<String, AnalysisStrategy> analysisStrategies = new HashMap<>();
    @Inject
    private DataContainerService dataContainer;

    static {
        analysisStrategies.put(IVR, new IVRAnalysisStrategy());
        analysisStrategies.put(IND, new INDAnalysisStrategy());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebAnalysisCriteria criteria = (WebAnalysisCriteria) req.getAttribute(CRITERIA);
        LOGGER.info("req getAttribute criteria strategy:" + criteria.getStrategy());

        WebAnalysisResult webAnalysisResult = this.getResult(criteria);
        LOGGER.info("req setAttribute result strategy:" + webAnalysisResult.getStrategy());

        if (webAnalysisResult.getErrorMessage() != null) {
            req.setAttribute(STRATEGY, webAnalysisResult.getStrategy());
            req.setAttribute(ERR_MESSAGE, webAnalysisResult.getErrorMessage());
            req.getRequestDispatcher("/analysisCriteria.jsp").forward(req, resp);
            return;
        }
        req.setAttribute(ANALYSIS_RESULT, webAnalysisResult);
        req.getRequestDispatcher("/analysisResult.jsp").forward(req, resp);
    }

    private WebAnalysisResult getResult(WebAnalysisCriteria criteria) {
        String strategy = criteria.getStrategy();
        return analysisStrategies.get(strategy)
                .getResult(criteria, dataContainer);
    }
}
