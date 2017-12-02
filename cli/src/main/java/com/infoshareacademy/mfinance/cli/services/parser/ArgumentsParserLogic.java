package com.infoshareacademy.mfinance.cli.services.parser;

import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.cli.services.parser.strategies.INDValidationStrategy;
import com.infoshareacademy.mfinance.cli.services.parser.strategies.IVRValidationStrategy;
import com.infoshareacademy.mfinance.cli.services.parser.strategies.ValidationStrategy;

import java.util.HashMap;
import java.util.Map;

public class ArgumentsParserLogic {

    private static Map<String, ValidationStrategy> validationStratiegies = new HashMap<>();

    static {
        validationStratiegies.put(analysisNames.IVR.toString(), new IVRValidationStrategy());
        validationStratiegies.put(analysisNames.IND.toString(), new INDValidationStrategy());
    }

    public ParserResult parse(String[] args) {
        if (args.length > 0) {
            String analysisCommandName = args[0];
            return validationStratiegies.get(analysisCommandName).validate(args);
        }
        return new ParserResult(false, "\nNo arguments passed to application.", null);
    }
}