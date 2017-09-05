package com.infoshare.web.container;

import com.infoshare.core.model.Investment;
import com.infoshare.core.model.MainContainer;

import java.util.List;

public interface IModelContainerService {
    List<Investment> getInvestments();

    MainContainer getMainContainer();

    void reload();
}
