package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.models.bossa.DataContainer;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class DataContainerBuilderTest {


    DataContainerBuilder dataContainerBuilder = new DataContainerBuilder();

    @Test
    public void buildDataContainer() {

        // TODO change this test to work with csv files from test/resources

        DataContainer container = dataContainerBuilder.getDataContainer();

        assertThat(container.getCurrenciesCount(), is(equalTo(35)));
        assertThat(container.getFundsCount(), is(equalTo(331)));
    }
}