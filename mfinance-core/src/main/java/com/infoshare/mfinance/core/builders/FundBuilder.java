package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.models.bossa.Fund;
import com.infoshare.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

class FundBuilder extends InvestmentBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundBuilder.class);
    private List<Fund> funds = new ArrayList<>();

    int getNumberOfFunds() {
        return funds.size();
    }
    List<Fund> getFunds() {
        return funds;
    }


    void createFundsFromFile(String filePath) {
        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();

            funds.add(new Fund(name, quotationList));

        } catch (Exception e) {
            LOGGER.error("Failed to build Fund:{}", e.getMessage());
        }
    }
}
