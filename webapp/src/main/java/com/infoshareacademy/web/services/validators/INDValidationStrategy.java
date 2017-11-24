package com.infoshareacademy.web.services.validators;

import com.infoshareacademy.web.model.analyzer.criterias.WebIndicatorCriteria;
import com.infoshareacademy.web.model.validation.ValidationResult;
import com.infoshareacademy.web.model.validation.forms.CriteriaForm;
import com.infoshareacademy.web.model.validation.forms.INDCriteriaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.*;

public class INDValidationStrategy implements ValidationStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(INDValidationStrategy.class);

    @Override
    public ValidationResult getValidationResult(HttpServletRequest req) {
        String investmentName = req.getParameter(INVESTMENT_NAME);
        String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);
        boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;

        INDCriteriaForm form = new INDCriteriaForm();
        form.setInvestmentName(investmentName);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<CriteriaForm>> violations = validator.validate(form);

        if (violations.size() > 0) {
            req.setAttribute(INVESTMENT_NAME, investmentName);
            req.setAttribute("violations", violations);

            return new ValidationResult(violations, req, null);
        }

        WebIndicatorCriteria criteria = new WebIndicatorCriteria();
        criteria.setInvestmentName(investmentName);
        criteria.setUserCustomName(userCustomName);
        criteria.setFavourite(isFavouriteChecked);

        return new ValidationResult(violations, req, criteria);
    }
}
