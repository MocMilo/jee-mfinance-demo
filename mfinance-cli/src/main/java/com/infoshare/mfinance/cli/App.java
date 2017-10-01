package com.infoshare.mfinance.cli;

import com.infoshare.mfinance.cli.model.results.AnalysisResult;
import com.infoshare.mfinance.cli.services.analyzer.CoreAnalyzer;
import com.infoshare.mfinance.cli.services.parser.ApplicationArgumentsParser;
import com.infoshare.mfinance.cli.services.parser.ParserResult;
import com.infoshare.mfinance.cli.view.WelcomeMessageComposer;

public class App {

    public static void main(String[] args) {

        new WelcomeMessageComposer().printWelcomeMessage(args);

        ParserResult parserResult = new ApplicationArgumentsParser().parse(args);

        if(!parserResult.isValid()){
            System.out.println(parserResult.getErrorMessage());
            return;
        }

        AnalysisResult analysisResult = new CoreAnalyzer().getResult(parserResult);

        // TODO consider results display in separate class
        System.out.println(analysisResult.toString());
    }
}
