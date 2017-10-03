package com.infoshare.mfinance.cli.services.parser;

import com.infoshare.mfinance.cli.services.parser.strategies.IVRValidationStrategy;
import com.infoshare.mfinance.cli.services.parser.strategies.ValidationStrategy;

import java.util.HashMap;
import java.util.Map;

public class ApplicationArgumentsParser {

    private static Map<String, ValidationStrategy> validationStratiegies = new HashMap<>();

    private ParserResult parserResult;

    static {

        //TODO impl the rest of validator strategies

        validationStratiegies.put(analysisNames.IVR.toString(), new IVRValidationStrategy());
    }

    public ParserResult parse(String[] args) {

        if (args.length > 0) {
            String analysisCommandName = args[0];
            return validationStratiegies.get(analysisCommandName).validate(args);
        }
        return new ParserResult(false, "\nNo arguments passed to application.", null);
    }
}