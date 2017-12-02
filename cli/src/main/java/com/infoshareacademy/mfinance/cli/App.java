package com.infoshareacademy.mfinance.cli;

import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.cli.services.analyzer.AnalyzerLogic;
import com.infoshareacademy.mfinance.cli.services.parser.ArgumentsParserLogic;
import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.cli.view.MessageComposer;

public class App {
    public static void main(String[] args) {
        ParserResult parserResult = new ArgumentsParserLogic().parse(args);
        new MessageComposer().printValidationMessage(parserResult);
        if (!parserResult.isValid()) {
            return;
        }
        AnalysisResult analysisResult = new AnalyzerLogic().getResult(parserResult);
        new MessageComposer().printResultMessage(analysisResult);
    }
}
