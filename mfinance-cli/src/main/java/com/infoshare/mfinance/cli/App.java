package com.infoshare.mfinance.cli;

import com.infoshare.mfinance.cli.model.results.AnalysisResult;
import com.infoshare.mfinance.cli.services.analyzer.CoreAnalyzer;
import com.infoshare.mfinance.cli.services.parser.ApplicationArgumentsParser;
import com.infoshare.mfinance.cli.services.parser.ParserResult;
import com.infoshare.mfinance.cli.view.MessageComposer;

public class App {

    public static void main(String[] args) {

        ParserResult parserResult = new ApplicationArgumentsParser().parse(args);

        if(!parserResult.isValid()){
            new MessageComposer().printMessage(parserResult);
            return;
        }

        AnalysisResult analysisResult = new CoreAnalyzer().getResult(parserResult);

        // TODO consider results display in separate class
        System.out.println(analysisResult.toString());
    }
}
