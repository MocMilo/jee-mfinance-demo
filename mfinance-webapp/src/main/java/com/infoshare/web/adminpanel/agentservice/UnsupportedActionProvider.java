package com.infoshare.web.adminpanel.agentservice;

import com.infoshare.web.adminpanel.trigger.ITriggerable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsupportedActionProvider implements ITriggerable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnsupportedActionProvider.class);

    @Override
    public void executeAction() {
        LOGGER.error("Unsupported task type!");
    }
}

