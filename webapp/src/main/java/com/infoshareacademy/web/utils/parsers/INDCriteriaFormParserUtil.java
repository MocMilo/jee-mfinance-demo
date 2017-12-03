package com.infoshareacademy.web.utils.parsers;

import com.infoshareacademy.web.model.validation.forms.INDCriteriaForm;

import javax.servlet.http.HttpServletRequest;

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.*;

public class INDCriteriaFormParserUtil {
    public static INDCriteriaForm parse(HttpServletRequest req) {
        String investmentName = req.getParameter(INVESTMENT_NAME);
        INDCriteriaForm form = new INDCriteriaForm();
        form.setInvestmentName(investmentName);
        return form;
    }
}
