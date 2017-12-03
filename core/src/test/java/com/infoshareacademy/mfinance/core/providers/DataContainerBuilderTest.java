package com.infoshareacademy.mfinance.core.providers;

import com.infoshareacademy.mfinance.core.builders.DataContainerBuilder;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class DataContainerBuilderTest {


    @Test
    public void buildDataContainer() throws IOException {
        DataContainerBuilder dataContainerBuilder = new DataContainerBuilder();
        DataContainer container = dataContainerBuilder.getDataContainer();
        assertTrue(container.getCurrenciesCount() > 0);
        assertTrue(container.getFundsCount() > 0);
    }
}