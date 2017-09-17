package com.infoshare.core.loader;

import junit.framework.TestCase;

import java.nio.file.Paths;

public class FundLoaderTest extends TestCase {

    private final String TESTFILEPATH = Paths.get("src/test/resources/csv/funds/AGI001.txt").toString();
    private final String TESTFILEPATH2 = Paths.get("src/test/resources/csv/funds/AIP001.txt").toString();
    private final String TESTFILEPATH3 = Paths.get("src/test/resources/csv/funds/ALL001.txt").toString();

    private final int NUMBEROFROWS = 405;
    private final int NUMBEROFROWS2 = 3742;
    private final int NUMBEROFROWS3 = 3303;

    FundLoader fundLoader = new FundLoader();



    public void testGetNumberOfFunds() throws Exception {

    }

    public void testGetFunds() throws Exception {

    }

    public void testCreateOneFundFromFile() throws Exception {

        fundLoader.createFundsFromFile(TESTFILEPATH);
        fundLoader.getNumberOfFunds();

        if (fundLoader.getFunds().size()!=1){
            throw new IllegalArgumentException();
        }
    }

    public void testCreateFundsFromFile() throws Exception {

        fundLoader.createFundsFromFile(TESTFILEPATH);
        fundLoader.createFundsFromFile(TESTFILEPATH2);
        fundLoader.getNumberOfFunds();

        if (fundLoader.getFunds().size()!=2){
            throw new IllegalArgumentException();
        }
    }

    public void testQuotationsInCreatedFundsFromFile() throws Exception {

        fundLoader.createFundsFromFile(TESTFILEPATH);
        fundLoader.createFundsFromFile(TESTFILEPATH2);
        fundLoader.createFundsFromFile(TESTFILEPATH3);

        fundLoader.getFunds().get(0);

        int numberOfQuotations = fundLoader.getFunds().get(0).getQuotations().size();

        System.out.println("number of quotations: "+numberOfQuotations);


        if (numberOfQuotations != NUMBEROFROWS){
            throw new IllegalArgumentException();
        }



    }



}