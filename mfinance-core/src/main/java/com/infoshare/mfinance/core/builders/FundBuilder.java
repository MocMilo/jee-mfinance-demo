package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.models.bossa.InvestmentFund;
import com.infoshare.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

class FundBuilder extends InvestmentBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundBuilder.class);
    private List<InvestmentFund> investmentFunds = new ArrayList<>();

    int getNumberOfFunds() {
        return investmentFunds.size();
    }
    List<InvestmentFund> getInvestmentFunds() {
        return investmentFunds;
    }


    void createFundsFromFile(String filePath) {
        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();

            investmentFunds.add(new InvestmentFund(name, quotationList));

        } catch (Exception e) {
            LOGGER.error("Failed to build InvestmentFund:{}", e.getMessage());
        }
    }
}
