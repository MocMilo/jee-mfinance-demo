package com.infoshareacademy.web.services.validators.classlevel;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IVRArgsValidator.class)
@Documented
public @interface ValidIVRDates {
    String message() default "Wrong dates order: buy date should be before sell date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
