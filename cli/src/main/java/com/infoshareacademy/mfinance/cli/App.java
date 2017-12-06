package com.infoshareacademy.mfinance.cli;

import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.cli.services.analyzer.AnalyzerLogic;
import com.infoshareacademy.mfinance.cli.services.parser.ArgumentsParserLogic;
import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.cli.services.provider.DataProvider;
import com.infoshareacademy.mfinance.cli.messages.MessageComposer;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;

public class App {
    public static void main(String[] args) {
        try {
            ParserResult parserResult = new ArgumentsParserLogic().parse(args);
            new MessageComposer().printValidationMessage(parserResult);

            if (!parserResult.isValid()) {
                return;
            }
            DataContainer container = new DataProvider().getDataContainer();
            AnalysisResult analysisResult = new AnalyzerLogic(container)
                    .getResult(parserResult);

            new MessageComposer().printResultMessage(analysisResult);
        } catch (Exception e) {
            System.out.println("Something went wrong. Exiting application.");
        }
    }
}
