package com.infoshareacademy.web.services.validators;


import com.infoshareacademy.mfinance.core.utils.BigDecimalUtil;
import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import com.infoshareacademy.web.model.validation.ValidationResult;
import com.infoshareacademy.web.model.validation.forms.CriteriaForm;
import com.infoshareacademy.web.model.validation.forms.IVRCriteriaForm;
import com.infoshareacademy.web.utils.constants.ConstantsProvider;
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

public class IVRValidationStrategy implements ValidationStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(IVRValidationStrategy.class);

    @Override
    public ValidationResult getValidationResult(HttpServletRequest req) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantsProvider.DATE_PATTERN);
        String investmentName = req.getParameter(ConstantsProvider.INVESTMENT_NAME);
        String capital = req.getParameter(ConstantsProvider.CAPITAL);
        String buyDate = req.getParameter(ConstantsProvider.BUY_DATE);
        String sellDate = req.getParameter(ConstantsProvider.SELL_DATE);
        String userCustomName = req.getParameter(ConstantsProvider.USER_FAVOURITE_CUSTOM_NAME);
        boolean isFavouriteChecked = req.getParameter(ConstantsProvider.IS_FAVOURITE) != null;

        IVRCriteriaForm form = new IVRCriteriaForm();
        form.setInvestmentName(investmentName);
        form.setCapital(capital);
        form.setBuyDate(buyDate);
        form.setSellDate(sellDate);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<CriteriaForm>> violations = validator.validate(form);

        if (violations.size() > 0) {
            req.setAttribute(ConstantsProvider.INVESTMENT_NAME, investmentName);
            req.setAttribute(ConstantsProvider.CAPITAL, capital);
            req.setAttribute(ConstantsProvider.BUY_DATE, buyDate);
            req.setAttribute(ConstantsProvider.SELL_DATE, sellDate);
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
        criteria.setFavourite(isFavouriteChecked);
        criteria.setUserCustomName(userCustomName);

        return new ValidationResult(violations, req, criteria);
    }
}
