package com.infoshare.mfinance.cli.services.parser.strategies;


import com.infoshare.mfinance.cli.model.arguments.IVRArgs;
import com.infoshare.mfinance.cli.services.parser.ParserResult;
import com.infoshare.mfinance.cli.services.parser.analysisNames;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        try {
            new BigDecimal(ivrArgs.getCapital());

        } catch (Exception e) {
            return new ParserResult(false, "\nwrong capital argument: should be decimal", null);
        }

        try {
            buyDate = LocalDate.parse(ivrArgs.getStartDate(), formatter);

        } catch (Exception e) {
            return new ParserResult(false, "\nwrong buy date: should be in format yyyy-MM-dd", null);
        }

        try {
            sellDate = LocalDate.parse(ivrArgs.getEndDate(), formatter);

        } catch (Exception e) {
            return new ParserResult(false, "\nwrong sell date: should be in format yyyy-MM-dd", null);
        }

        if (sellDate.isBefore(buyDate)) {
            return new ParserResult(false, "\nwrong dates order: buy date should be before sell date", null);
        }

        return new ParserResult(true, "", ivrArgs);
    }
}
