package com.infoshare.mfinance.cli.model.arguments;

import com.infoshare.mfinance.cli.utils.ValidatorUtil;
import org.junit.Before;
import org.junit.Test;

import javax.validation.*;
import java.util.Set;


public class IVRArgsTest {
    IVRArgs args = new IVRArgs();

    @Before
    public void init(){

        args.setStrategy("IVR");
        args.setInvestmentName("USD");
        args.setCapital("1000.00");
        args.setStartDate("2017-01-15");
        args.setEndDate("2017-01-17");
    }

    @Test
    public void shouldReturnValidationViolations(){

       Validator validator =  ValidatorUtil.getValidator();

        Set<ConstraintViolation<IVRArgs>> constraintViolations =
                validator.validate(args);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation item : constraintViolations){
                System.out.println(item.getMessage());
            }
        }
    }
}