package com.infoshare.core.loader;

import com.infoshare.core.models.bossa.Fund;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class FundLoaderTest {

    private final String TESTFILEPATH = Paths.get("src/test/resources/csv/funds/AGI001.txt").toString();
    /*private final String TESTFILEPATH2 = Paths.get("src/test/resources/csv/funds/AIP001.txt").toString();
    private final String TESTFILEPATH3 = Paths.get("src/test/resources/csv/funds/ALL001.txt").toString();
*/
    private final int NUMBEROFROWS = 405;
/*    private final int NUMBEROFROWS2 = 3742;
    private final int NUMBEROFROWS3 = 3303;*/

    private FundLoader fundLoader;

    @Before
    public void init() {
    fundLoader = new FundLoader();
    fundLoader.createFundsFromFile(TESTFILEPATH);
    }

    @Test
    public void testCreateOneFundFromFile() throws Exception {

        int expected = fundLoader.getNumberOfFunds();
        assertThat(expected, is(equalTo(1)));
    }

    public void testCreateFundsFromFile() throws Exception {

        Fund fund = fundLoader.getFunds().get(0);
        String expectedName = "";

        assertThat(fund, not(equalTo(nullValue())));
        assertThat(fund.getName(), is(equalTo("")));
    }

    @Test
    public void testQuotationsInCreatedFundsFromFile() throws Exception {

        int numberOfQuotations = fundLoader.getFunds().get(0).getQuotations().size();

        assertThat(NUMBEROFROWS, is(numberOfQuotations));

    }
}