package com.infoshare.mfinance.core.providers;

import com.infoshare.mfinance.core.builders.DataContainerBuilder;
import com.infoshare.mfinance.core.models.bossa.DataContainer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class DataContainerBuilderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataContainerBuilderTest.class);
    private DataContainerBuilder dataContainerBuilder = new DataContainerBuilder();

    @Test
    public void buildDataContainer() {

        // TODO change this test to work with csv files from test/resources not from main/resources

        DataContainer container = dataContainerBuilder.getDataContainer();

        LOGGER.info("number of currencies in data container:{}", container.getCurrenciesCount());
        LOGGER.info("number of funds in data container:{}", container.getFundsCount());
        LOGGER.info("number of investments in data container:" + container.getInvestments().size());

        assertTrue(container.getCurrenciesCount() > 0);
        assertTrue(container.getFundsCount() > 0);
    }
}