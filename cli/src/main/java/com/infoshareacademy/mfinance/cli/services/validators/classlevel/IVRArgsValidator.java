package com.infoshareacademy.mfinance.cli.services.validators.classlevel;

import com.infoshareacademy.mfinance.cli.model.arguments.IVRArgs;
import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IVRArgsValidator implements ConstraintValidator<ValidIVRArgs, IVRArgs> {

    @Override
    public void initialize(ValidIVRArgs constraintAnnotation) {
    }

    @Override
    public boolean isValid(IVRArgs ivrArgs, ConstraintValidatorContext constraintValidatorContext) {
        return LocalDateUtil.parseForm(ivrArgs.getStartDate())
                .isBefore(LocalDateUtil.parseForm(ivrArgs.getEndDate()));
    }
}
