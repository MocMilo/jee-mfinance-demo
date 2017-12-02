package com.infoshareacademy.web.servlets.logic;

import com.infoshareacademy.web.services.validators.INDValidationStrategy;
import com.infoshareacademy.web.services.validators.IVRValidationStrategy;
import com.infoshareacademy.web.model.validation.ValidationResult;
import com.infoshareacademy.web.services.validators.ValidationStrategy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = "/validation")
public class ValidationStrategiesServlet extends HttpServlet {
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
            req.getRequestDispatcher("/analysisCriteria.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("criteria", validationResult.getCriteria());
        req.getRequestDispatcher("/favourite").forward(req, resp);
    }

    private ValidationResult getValidationResult(HttpServletRequest req) {
        String strategy = req.getParameter("strategy");
        return validationStrategies.get(strategy).getValidationResult(req);
    }
}
