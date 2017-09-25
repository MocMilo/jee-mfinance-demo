package com.infoshare.controller.validators.argument;

import com.infoshare.model.validationResults.ArgValidationResult;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

public class DateFormatValidatorTest {

    private static final String ERR_MESSAGE = "Value should be Date, formated: yyyy-MM-dd";
    private static final String ISVALID_MESSAGE = "ok";
    String validDateValue = "2017-07-10";
    String notValidDateValue = "20170710";
    String emptyString = "";
    DateFormatValidator dateFormatValidator = new DateFormatValidator();

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
    public void doValidateValidDateFormatValue() throws Exception {

        boolean isValidAssumption = true;
        ArgValidationResult resultComposite = dateFormatValidator
                .doValidate(validDateValue);

        doCommonAssertions(isValidAssumption, resultComposite);
    }

    @Test
    public void doValidateNotValidBigDecimalValue() throws Exception {

        boolean isValidAssumption = false;
        ArgValidationResult resultComposite = dateFormatValidator
                .doValidate(notValidDateValue);

        doCommonAssertions(isValidAssumption, resultComposite);
    }

    @Test
    public void doValidateEmptyStringValue() throws Exception {

        boolean isValidAssumption = false;
        ArgValidationResult resultComposite = dateFormatValidator
                .doValidate(emptyString);

        doCommonAssertions(isValidAssumption, resultComposite);
    }

    @Test(expected = NullPointerException.class)
    public void doValidateNullValue() throws Exception {

        boolean isValidAssumption = false;
        ArgValidationResult resultComposite = dateFormatValidator
                .doValidate(null);

        doCommonAssertions(isValidAssumption, resultComposite);
    }


}