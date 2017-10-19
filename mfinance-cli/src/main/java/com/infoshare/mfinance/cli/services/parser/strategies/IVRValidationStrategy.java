package com.infoshare.mfinance.cli.services.parser.strategies;


import com.infoshare.mfinance.cli.model.arguments.IVRArgs;
import com.infoshare.mfinance.cli.services.parser.ParserResult;
import com.infoshare.mfinance.cli.services.parser.analysisNames;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IVRValidationStrategy implements ValidationStrategy {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate buyDate;
    private LocalDate sellDate;


    @Override
    public ParserResult validate(String[] args) {

        if (args.length != 5) {
            return new ParserResult(false, "\nwrong number of arguments", null);
        }

        IVRArgs ivrArgs = new IVRArgs(args);

        try {
            analysisNames.valueOf(ivrArgs.getStrategy());
        } catch (NullPointerException e) {
            return new ParserResult(false, "\nanalysis not found!", null);
        }

        if (ivrArgs.getInvestmentName().equals("")) {
            return new ParserResult(false, "\nwrong investment name, should not be empty", null);
        }

        if (!hasMoneyFormat(ivrArgs.getCapital())) {
            return new ParserResult(false, "\nwrong capital argument: should be decimal of format: 1.00", null);
        }

        try {
            new BigDecimal(ivrArgs.getCapital());
        } catch (NumberFormatException e) {
            return new ParserResult(false, "\nwrong capital argument: should be decimal of format: 1.00", null);
        }

        if (new BigDecimal((ivrArgs.getCapital())).compareTo(BigDecimal.ZERO) <= 0) {
            return new ParserResult(false, "\nwrong capital argument: should be > 0.00", null);
        }

        try {
            buyDate = LocalDate.parse(ivrArgs.getStartDate(), formatter);
        } catch (DateTimeParseException e) {
            return new ParserResult(false, "\nwrong buy date: should be in format yyyy-MM-dd", null);
        }

        try {
            sellDate = LocalDate.parse(ivrArgs.getEndDate(), formatter);
        } catch (DateTimeParseException e) {
            return new ParserResult(false, "\nwrong sell date: should be in format yyyy-MM-dd", null);
        }

        if (sellDate.isBefore(buyDate)) {
            return new ParserResult(false, "\nwrong dates order: buy date should be before sell date", null);
        }

        return new ParserResult(true, "", ivrArgs);
    }

    private boolean hasMoneyFormat(String evalValue) {
        return new Scanner(evalValue).hasNext(Pattern.compile("^(0|0?[1-9]\\d*)\\.\\d\\d$"));
    }

}
