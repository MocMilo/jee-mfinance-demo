package com.infoshare.mfinance.cli.services.analyzer;

import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.mfinance.cli.model.results.AnalysisResult;
import com.infoshare.mfinance.cli.services.analyzer.initializer.BossaInitializerFacade;
import com.infoshare.mfinance.cli.services.analyzer.strategies.AnalysisStrategy;
import com.infoshare.mfinance.cli.services.analyzer.strategies.INDAnalysisStrategy;
import com.infoshare.mfinance.cli.services.analyzer.strategies.IVRAnalysisStrategy;
import com.infoshare.mfinance.cli.services.parser.ParserResult;
import com.infoshare.mfinance.cli.services.parser.analysisNames;

import java.util.HashMap;
import java.util.Map;


public class CoreAnalyzer {


    private static Map<String, AnalysisStrategy> analysisStratiegies = new HashMap<>();

    static {

        analysisStratiegies.put(analysisNames.IND.toString(), new INDAnalysisStrategy());
        analysisStratiegies.put(analysisNames.IVR.toString(), new IVRAnalysisStrategy());
    }

    public AnalysisResult getResult(ParserResult result) {

        BossaInitializerFacade bossaInitializerFacade = new BossaInitializerFacade();
        MainContainer container = bossaInitializerFacade.getMainContainerWithLoadedData();

        String analysisCommandName = result.getArguments().getStrategy();

        return analysisStratiegies.get(analysisCommandName)
                .getResult(result, container);
    }
}
