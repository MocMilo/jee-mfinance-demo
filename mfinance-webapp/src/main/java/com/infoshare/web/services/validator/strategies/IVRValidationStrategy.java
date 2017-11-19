package com.infoshare.web.services.validator.strategies;


import com.infoshare.mfinance.core.utils.BigDecimalUtil;
import com.infoshare.web.model.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.criterias.WebInvestmentRevenueCriteria;
import com.infoshare.web.model.validation.ValidationResult;
import com.infoshare.web.model.validation.forms.CriteriaForm;
import com.infoshare.web.model.validation.forms.IVRCriteriaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static com.infoshare.web.utils.ConstantsProvider.*;

public class IVRValidationStrategy implements ValidationStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(IVRValidationStrategy.class);

    @Override
    public ValidationResult getValidationResult(HttpServletRequest req) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String investmentName = req.getParameter(INVESTMENT_NAME);
        String capital = req.getParameter(CAPITAL);
        String buyDate = req.getParameter(BUY_DATE);
        String sellDate = req.getParameter(SELL_DATE);
        String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);
        Boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;

        IVRCriteriaForm form = new IVRCriteriaForm();
        form.setInvestmentName(investmentName);
        form.setCapital(capital);
        form.setBuyDate(buyDate);
        form.setSellDate(sellDate);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<CriteriaForm>> violations = validator.validate(form);

        if (violations.size() > 0) {
            req.setAttribute(INVESTMENT_NAME, investmentName);
            req.setAttribute(CAPITAL, capital);
            req.setAttribute(BUY_DATE, buyDate);
            req.setAttribute(SELL_DATE, sellDate);
            req.setAttribute("violations", violations);

            return new ValidationResult(violations, req, null);
        }

        BigDecimal investmentCapital = BigDecimalUtil.parseMoney(capital);

        LocalDate investmentBuyDate = LocalDate.parse(buyDate, formatter);
        LocalDate investmentSellDate = LocalDate.parse(sellDate, formatter);

        WebInvestmentRevenueCriteria criteria = new WebInvestmentRevenueCriteria();
        criteria.setInvestmentName(investmentName);
        criteria.setInvestedCapital(investmentCapital);
        criteria.setBuyDate(investmentBuyDate);
        criteria.setSellDate(investmentSellDate);

        WebAnalysisCriteria webAnalysisCriteria = criteria;

        return new ValidationResult(violations, req, criteria);
    }
}
