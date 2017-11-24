package com.infoshareacademy.web.services.validators.classlevel;

import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import com.infoshareacademy.web.model.validation.forms.IVRCriteriaForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IVRArgsValidator implements ConstraintValidator<ValidIVRDates, IVRCriteriaForm> {

    @Override
    public void initialize(ValidIVRDates constraintAnnotation) {

    }

    @Override
    public boolean isValid(IVRCriteriaForm ivrArgs, ConstraintValidatorContext constraintValidatorContext) {

        return LocalDateUtil.parseForm(ivrArgs.getBuyDate())
                .isBefore(LocalDateUtil.parseForm(ivrArgs.getSellDate()));
    }
}
