package com.infoshare.controller.validators.argument;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class BigDecimalValidatorTest {

    String validBigDecimalValue ="1000.00";
    String notValidBigDecimalValue ="1000.err00";

    BigDecimalValidator bigDecimalValidator = new BigDecimalValidator();

    @Test
    public void doValidateValidBigDecimalValue() throws Exception {

    boolean isValid = true;
    boolean validationResult = bigDecimalValidator
            .doValidate(validBigDecimalValue)
            .isValid();

    assertThat(validationResult, is(instanceOf(boolean.class)));
    assertThat(validationResult, is(equalTo(isValid)));
    }

    @Test
    public void doValidateNotValidBigDecimalValue() throws Exception {

        boolean isValid = false;
        boolean validationResult = bigDecimalValidator
                .doValidate(notValidBigDecimalValue)
                .isValid();

        assertThat(validationResult, is(instanceOf(boolean.class)));
        assertThat(validationResult, is(equalTo(isValid)));
    }
}