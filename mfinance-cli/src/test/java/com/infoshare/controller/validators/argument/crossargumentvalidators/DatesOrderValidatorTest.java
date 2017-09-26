package com.infoshare.controller.validators.argument.crossargumentvalidators;

import com.infoshare.model.validationResults.ArgValidationResult;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


public class DatesOrderValidatorTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String ERR_MESSAGE_PART="Wrong date args order.";
    private LocalDate firstDate = LocalDate.parse("2015-09-17", FORMATTER);
    private LocalDate secondDate = LocalDate.parse("2015-09-24", FORMATTER);
    private List<LocalDate> orderedDates = new LinkedList<>();
    ArgValidationResult result;

    @Test
    public void doValidateWhenOrderIsCorrect() throws Exception {

        orderedDates.add(firstDate);
        orderedDates.add(secondDate);

        result = new DatesOrderValidator(orderedDates).doValidate();

        assertThat(result, not(equalTo(nullValue())));
        assertThat(result.isValid(), is(equalTo(true)));
        assertThat(result.getErrMessage(),not(containsString(ERR_MESSAGE_PART)));
    }

    @Test
    public void doValidateWhenOrderIsNotCorrect() throws Exception {

        orderedDates.add(secondDate);
        orderedDates.add(firstDate);

        result = new DatesOrderValidator(orderedDates).doValidate();

        assertThat(result, not(equalTo(nullValue())));
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getErrMessage(), not(equalTo(nullValue())));
        assertThat(result.getErrMessage(), containsString(ERR_MESSAGE_PART));
    }

    @Test (expected = DateTimeParseException.class)
    public void doValidateWhenNotValidatedDateArgIsPassed() throws Exception {

        LocalDate notParsableDate = LocalDate.parse("20err15-09-24", FORMATTER);

        orderedDates.add(firstDate);
        orderedDates.add(notParsableDate);
        result = new DatesOrderValidator(orderedDates).doValidate();
    }

}