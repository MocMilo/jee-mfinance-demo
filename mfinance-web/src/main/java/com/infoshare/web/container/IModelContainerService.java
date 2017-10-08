package com.infoshare.web.container;

import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.mfinance.core.models.bossa.MainContainer;

import java.util.List;

public interface IModelContainerService {
    List<Investment> getInvestments();

    MainContainer getMainContainer();

    void reload();
}
