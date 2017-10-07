package com.infoshare.core.loader;

import com.infoshare.core.models.bossa.Fund;
import com.infoshare.core.models.bossa.Quotation;


import java.util.ArrayList;
import java.util.List;

public class FundLoader extends Loader {

    private List<Fund> funds = new ArrayList<>();

    public int getNumberOfFunds() {
        return funds.size();
    }

    public List<Fund> getFunds() {
        return funds;
    }


    public void createFundsFromFile(String filePath) {
        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();
            int id = funds.size();

            //....//

            Fund fund = new Fund(id, name, quotationList);
            funds.add(fund);

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
