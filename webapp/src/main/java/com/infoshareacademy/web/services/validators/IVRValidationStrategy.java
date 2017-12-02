package com.infoshareacademy.web.services.validators;

import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import com.infoshareacademy.web.model.validation.ValidationResult;
import com.infoshareacademy.web.model.validation.forms.CriteriaForm;
import com.infoshareacademy.web.model.validation.forms.IVRCriteriaForm;
import com.infoshareacademy.web.utils.converters.IVRCriteriaFormParserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.*;

public class IVRValidationStrategy implements ValidationStrategy {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public ValidationResult getValidationResult(HttpServletRequest req) {
        final IVRCriteriaForm form = IVRCriteriaFormParserUtil.parse(req);
        final boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;
        final String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);

        Set<ConstraintViolation<CriteriaForm>> violations = validator.validate(form);
        if (!violations.isEmpty()) {
            req.setAttribute(CRITERIA_FORM, form);
            req.setAttribute(VIOLATIONS, violations);
            return new ValidationResult(violations, req, null);
        }
        WebInvestmentRevenueCriteria criteria = new WebInvestmentRevenueCriteria(form,
                userCustomName, isFavouriteChecked);

        return new ValidationResult(violations, req, criteria);
    }
}
