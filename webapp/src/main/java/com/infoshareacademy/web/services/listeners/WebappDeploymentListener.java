package com.infoshareacademy.web.services.listeners;

import com.infoshareacademy.web.services.bossa.DataContainerService;
import com.infoshareacademy.web.services.persistence.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.TimeZone;

@WebListener
public final class WebappDeploymentListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebappDeploymentListener.class);
    private static final String DEFAULT_TIMEZONE = "UTC";
    @Inject
    private DataContainerService container;
    @Inject
    private UserService userService;

    /**
     * When ServletContextEvent is initialized,
     * default account with Admin role is added to database by UserService.
     * Properties of default Admin account are stored in /resources/configuration/webconfiguration.json
     * (this file should be configured before application build and deployment).
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("Application context Initialized.");
        this.setApplicationDefaultTimeZone(DEFAULT_TIMEZONE);
        this.loadDataContainer();
        userService.addDefaultAdminUser();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("Application Servlet Context Destroyed.");
    }

    private void loadDataContainer() {
        try {
            LOGGER.info("Data models CSV files loading initialized...");
            container.getDataContainer();
            LOGGER.info("Currencies items:{} Funds items:{}",
                    container.getDataContainer().getCurrenciesCount(),
                    container.getDataContainer().getFundsCount());
        } catch (RuntimeException ex) {
            LOGGER.error("Failed to load data models CSV files! {}", ex.getMessage());
        }
    }

    private void setApplicationDefaultTimeZone(String timeZone) {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        LOGGER.info("Default application timezone set to: {}", TimeZone.getDefault());
    }
}
