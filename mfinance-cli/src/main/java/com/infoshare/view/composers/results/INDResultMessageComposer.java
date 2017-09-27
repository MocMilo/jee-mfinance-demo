package com.infoshare.view.composers.results;

import com.infoshare.model.analysisResults.INDResult;

public class INDResultMessageComposer {


    public void printResultMessage(INDResult result) {

        System.out.print("\nname:" + result.getName());

        System.out.print("\nfirst quot date:" + result.getFirstCLIQuotation().getDate());
        System.out.print("\nlast quot date:" + result.getLastCLIQuotation().getDate());

        System.out.print("\nmaxDelta+ quot date:" + result.getMaxDeltaMinus().getDate());
        System.out.print("\nmaxDelta+ quot value[PLN]:" + result.getMaxDeltaMinus().getClose());
        System.out.print("\nmaxDelta+ quot delta[%]:" + result.getMaxDeltaMinus().getDeltaClose());

        System.out.print("\nmaxDelta- quot date:" + result.getMaxDeltaPlus().getDate());
        System.out.print("\nmaxDelta- quot value[%]:" + result.getMaxDeltaPlus().getClose());
        System.out.print("\nmaxDelta- quot delta[%]:" + result.getMaxDeltaPlus().getDeltaClose());

        System.out.print("\nmaxValue quot date:" + result.getMaxValueCLIQuotation().getDate());
        System.out.print("\nmaxValue quot value[%]:" + result.getMaxValueCLIQuotation().getClose());
        System.out.print("\nmaxValue quot delta[%]:" + result.getMaxValueCLIQuotation().getDeltaClose());

        System.out.print("\nminValue quot date:" + result.getMinValueCLIQuotation().getDate());
        System.out.print("\nminValue quot value[%]:" + result.getMinValueCLIQuotation().getClose());
        System.out.print("\nminValue quot delta[%]:" + result.getMinValueCLIQuotation().getDeltaClose());
    }


}
