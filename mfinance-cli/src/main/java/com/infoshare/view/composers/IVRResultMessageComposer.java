package com.infoshare.view.composers;

import com.infoshare.model.analysisResults.IVRResult;

public class IVRResultMessageComposer {

    public void printResultMessage(IVRResult result) {

        System.out.print("\ndelta [%]:" + result.getCapitalRevenueDeltaPercentValue());
        System.out.print("\nCapitalRevene [PLN]:" + result.getCapitalRevenueValue());
    }

}
