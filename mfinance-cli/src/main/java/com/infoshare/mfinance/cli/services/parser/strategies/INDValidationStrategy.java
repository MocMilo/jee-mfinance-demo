package com.infoshare.mfinance.cli.services.parser.strategies;

import com.infoshare.mfinance.cli.model.arguments.INDArgs;
import com.infoshare.mfinance.cli.services.parser.ParserResult;
import com.infoshare.mfinance.cli.services.parser.analysisNames;

public class INDValidationStrategy implements ValidationStrategy {


    @Override
    public ParserResult validate(String[] args) {

        if (args.length != 2) {
            return new ParserResult(false, "\nwrong number of arguments", null);
        }

        INDArgs indArgs = new INDArgs(args);

        try {
            analysisNames.valueOf(indArgs.getStrategy());
        } catch (NullPointerException e) {
            return new ParserResult(false, "\nanalysis not found!", null);
        }

        return new ParserResult(true, "", indArgs);
    }
}
