package com.infoshare.web.services.administration.agentservice;

import com.infoshare.web.services.administration.agentservice.trigger.ITriggerable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsupportedActionProvider implements ITriggerable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnsupportedActionProvider.class);

    @Override
    public void executeAction() {
        LOGGER.error("Unsupported task type!");
    }
}

