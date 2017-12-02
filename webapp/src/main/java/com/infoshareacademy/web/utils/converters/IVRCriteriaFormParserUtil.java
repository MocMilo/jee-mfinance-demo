package com.infoshareacademy.web.utils.converters;

import com.infoshareacademy.web.model.validation.forms.IVRCriteriaForm;

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.*;

import javax.servlet.http.HttpServletRequest;

public class IVRCriteriaFormParserUtil {

    public static IVRCriteriaForm parse(HttpServletRequest req) {
        IVRCriteriaForm form = new IVRCriteriaForm();
        form.setInvestmentName(req.getParameter(INVESTMENT_NAME));
        form.setCapital(req.getParameter(CAPITAL));
        form.setBuyDate(req.getParameter(BUY_DATE));
        form.setSellDate(req.getParameter(SELL_DATE));
        return form;
    }
}
