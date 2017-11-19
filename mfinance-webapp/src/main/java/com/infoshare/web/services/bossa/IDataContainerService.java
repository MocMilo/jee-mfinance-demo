package com.infoshare.web.services.bossa;

import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.mfinance.core.models.bossa.DataContainer;

import java.util.List;

public interface IDataContainerService {
    List<Investment> getInvestments();

    DataContainer getDataContainer();

    void reload();
}
