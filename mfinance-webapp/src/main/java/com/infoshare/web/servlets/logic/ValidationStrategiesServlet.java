package com.infoshare.web.servlets.logic;

import com.infoshare.web.services.validators.INDValidationStrategy;
import com.infoshare.web.services.validators.IVRValidationStrategy;
import com.infoshare.web.model.validation.ValidationResult;
import com.infoshare.web.services.validators.ValidationStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = "/auth/userview/validation")
public class ValidationStrategiesServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzerStrategiesServlet.class);
    private static Map<String, ValidationStrategy> validationStrategies = new HashMap<>();

    static {
        validationStrategies.put("IVR", new IVRValidationStrategy());
        validationStrategies.put("IND", new INDValidationStrategy());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ValidationResult validationResult = this.getValidationResult(req);

        if (!validationResult.getViolations().isEmpty()) {

            req = validationResult.getReq();
            req.getRequestDispatcher("../userview/analysisCriteria.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("criteria", validationResult.getCriteria());


        req.getRequestDispatcher("/auth/userview/favourites").forward(req, resp);

       /* req.getRequestDispatcher("/auth/userview/analysis").forward(req, resp);*/

    }

    private ValidationResult getValidationResult(HttpServletRequest req) {

        String strategy = req.getParameter("strategy");
        return validationStrategies.get(strategy).getValidationResult(req);
    }
}
