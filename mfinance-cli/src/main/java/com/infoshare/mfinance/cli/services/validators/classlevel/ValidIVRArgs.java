package com.infoshare.mfinance.cli.services.validators.classlevel;




import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IVRArgsValidator.class)
@Documented
public @interface ValidIVRArgs {

        String message () default "Wrong dates order: buy date should be before sell date.";
        Class<?>[] groups () default {};
        Class<? extends Payload>[] payload () default {};
}
