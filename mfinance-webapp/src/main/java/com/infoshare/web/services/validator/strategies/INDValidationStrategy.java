package com.infoshare.web.services.validator.strategies;

import com.infoshare.web.model.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.criterias.WebIndicatorCriteria;
import com.infoshare.web.model.criterias.WebInvestmentRevenueCriteria;
import com.infoshare.web.model.validation.ValidationResult;
import com.infoshare.web.model.validation.forms.CriteriaForm;
import com.infoshare.web.model.validation.forms.INDCriteriaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.infoshare.web.utils.ConstantsProvider.*;

public class INDValidationStrategy implements ValidationStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(INDValidationStrategy.class);


    @Override
    public ValidationResult getValidationResult(HttpServletRequest req) {
        String investmentName = req.getParameter(INVESTMENT_NAME);
        String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);
        Boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;

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

        WebAnalysisCriteria webAnalysisCriteria = criteria;

        return new ValidationResult(violations, req, criteria);
    }
}
