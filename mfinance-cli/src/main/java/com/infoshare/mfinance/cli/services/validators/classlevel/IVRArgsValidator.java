package com.infoshare.mfinance.cli.services.validators.classlevel;

import com.infoshare.mfinance.cli.model.arguments.IVRArgs;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IVRArgsValidator implements ConstraintValidator<ValidIVRArgs, IVRArgs> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(ValidIVRArgs constraintAnnotation) {

    }

    @Override
    public boolean isValid(IVRArgs ivrArgs, ConstraintValidatorContext constraintValidatorContext) {

        return LocalDate.parse(ivrArgs.getStartDate(), formatter)
                .isBefore(LocalDate.parse(ivrArgs.getEndDate(), formatter));
    }
}
