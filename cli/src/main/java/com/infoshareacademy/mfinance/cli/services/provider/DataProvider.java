package com.infoshareacademy.mfinance.cli.services.provider;

import com.infoshareacademy.mfinance.core.builders.DataContainerBuilder;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;

import java.io.IOException;

public class DataProvider {
    public DataContainer getDataContainer() throws IOException {
        DataContainer dataContainer;
        try {
            System.out.println("Loading data from remote location...");
            dataContainer = new DataContainerBuilder().getDataContainer();
        } catch (IOException e) {
            System.out.println("Failed to build DataContainer. Possible internet connection problem.");
            throw new IOException(e);
        }
        return dataContainer;
    }
}
