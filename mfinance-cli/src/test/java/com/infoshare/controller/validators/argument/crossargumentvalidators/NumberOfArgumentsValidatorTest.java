package com.infoshare.controller.validators.argument.crossargumentvalidators;

import com.infoshare.model.arguments.IVRArgs;
import com.infoshare.model.validationResults.ArgValidationResult;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class NumberOfArgumentsValidatorTest {

    private static final String ERR_MESSAGE_PART = "Wrong number of arguments:";
    private NumberOfArgumentsValidator validator;
    private ArgValidationResult result;

    @Test
    public void doValidateProperNumberOfArgs() throws Exception {

        String[] evaluatedArgs = {"IVR", "USD", "1000", "2015-09-17", "2015-09-18"};

        validator = new NumberOfArgumentsValidator(IVRArgs.COMMAND_ARGS_NUMBER, evaluatedArgs);
        result = validator.doValidate();

        assertThat(result, not(equalTo(nullValue())));
        assertThat(result.isValid(), not(equalTo(nullValue())));
        assertThat(result.isValid(), is(equalTo(true)));
        assertThat(result.getErrMessage(), not(equalTo(nullValue())));
        assertThat(result.getErrMessage(), containsString(""));
    }

    @Test
    public void doValidateInsufficientNumberOfArgs() throws Exception {

        String[] evaluatedArgs = {"IVR", "USD", "1000", "2015-09-17"};

        validator = new NumberOfArgumentsValidator(IVRArgs.COMMAND_ARGS_NUMBER, evaluatedArgs);
        result = validator.doValidate();
        assertThat(result, not(equalTo(nullValue())));
        assertThat(result.isValid(), not(equalTo(nullValue())));
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getErrMessage(), not(equalTo(nullValue())));
        assertThat(result.getErrMessage(), containsString(ERR_MESSAGE_PART));
    }

    @Test
    public void doValidateTooManyArgs() throws Exception {

        String[] evaluatedArgs = {"IVR", "USD", "1000", "2015-09-17", "2015-09-18", "2015-09-18"};

        validator = new NumberOfArgumentsValidator(IVRArgs.COMMAND_ARGS_NUMBER, evaluatedArgs);
        result = validator.doValidate();
        assertThat(result, not(equalTo(nullValue())));
        assertThat(result.isValid(), not(equalTo(nullValue())));
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getErrMessage(), not(equalTo(nullValue())));
        assertThat(result.getErrMessage(), containsString(ERR_MESSAGE_PART));

    }
}