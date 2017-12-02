package com.infoshareacademy.web.services.validators;

import com.infoshareacademy.web.model.analyzer.criterias.WebIndicatorCriteria;
import com.infoshareacademy.web.model.validation.ValidationResult;
import com.infoshareacademy.web.model.validation.forms.CriteriaForm;
import com.infoshareacademy.web.model.validation.forms.INDCriteriaForm;
import com.infoshareacademy.web.utils.converters.INDCriteriaFormParserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.*;

public class INDValidationStrategy implements ValidationStrategy {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public ValidationResult getValidationResult(HttpServletRequest req) {
        final INDCriteriaForm form = INDCriteriaFormParserUtil.parse(req);
        final String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);
        final boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;

        Set<ConstraintViolation<CriteriaForm>> violations = validator.validate(form);
        if (!violations.isEmpty()) {
            req.setAttribute(CRITERIA_FORM, form);
            req.setAttribute("violations", violations);
            return new ValidationResult(violations, req, null);
        }
        WebIndicatorCriteria criteria = new WebIndicatorCriteria(form,
                userCustomName,isFavouriteChecked);

        return new ValidationResult(violations, req, criteria);
    }
}
