package com.infoshare.controller.validators.argument;

import com.infoshare.model.validationResults.ArgValidationResult;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

public class BigDecimalValidatorTest {

    private static final String ERR_MESSAGE = "Value should be of type Decimal";
    private static final String ISVALID_MESSAGE = "ok";
    String validBigDecimalValue = "1000.00";
    String notValidBigDecimalValue = "1000.err00";
    String emptyString = "";
    BigDecimalValidator bigDecimalValidator = new BigDecimalValidator();

    private void doCommonAssertions(boolean isValidAssumption, ArgValidationResult resultComposite) {

        boolean validationResult = resultComposite.isValid();
        String message = resultComposite.getErrMessage();
        String evaluatedValue = resultComposite.getEvaluatedValue();

        assertThat(validationResult, notNullValue());
        assertThat(validationResult, is(equalTo(isValidAssumption)));

        assertThat(message, notNullValue());
        assertThat(message, not(equalTo("")));
        if(isValidAssumption){assertThat(message, is(equalTo(ISVALID_MESSAGE)));}
        if(!isValidAssumption){assertThat(message, is(equalTo(ERR_MESSAGE)));}

        assertThat(evaluatedValue, notNullValue());
    }

    @Test
    public void doValidateValidBigDecimalValue() throws Exception {

        boolean isValidAssumption = true;
        ArgValidationResult resultComposite = bigDecimalValidator
                .doValidate(validBigDecimalValue);

        doCommonAssertions(isValidAssumption, resultComposite);
    }

    @Test
    public void doValidateNotValidBigDecimalValue() throws Exception {

        boolean isValidAssumption = false;
        ArgValidationResult resultComposite = bigDecimalValidator
                .doValidate(notValidBigDecimalValue);

        doCommonAssertions(isValidAssumption, resultComposite);
    }

    @Test
    public void doValidateEmptyStringValue() throws Exception {

        boolean isValidAssumption = false;
        ArgValidationResult resultComposite = bigDecimalValidator
                .doValidate(emptyString);

        doCommonAssertions(isValidAssumption, resultComposite);
    }

    @Test(expected = NullPointerException.class)
    public void doValidateNullValue() throws Exception {

        boolean isValidAssumption = false;
        ArgValidationResult resultComposite = bigDecimalValidator
                .doValidate(null);

        doCommonAssertions(isValidAssumption, resultComposite);
    }
}