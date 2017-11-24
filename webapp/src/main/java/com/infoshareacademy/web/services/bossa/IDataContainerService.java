package com.infoshareacademy.web.services.bossa;

import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;

public interface IDataContainerService {

    DataContainer getDataContainer();
    void reload();
}
