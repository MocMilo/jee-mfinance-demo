package com.infoshareacademy.web.services.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

@WebListener
public class UserSessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        Instant instant = Instant.ofEpochMilli(httpSessionEvent.getSession().getCreationTime());
        LocalDateTime sessionCreated = LocalDateTime.ofInstant(instant, UTC);
        LOGGER.info("SessionId:{} Created:{}  ", httpSessionEvent.getSession().getId(), sessionCreated);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        LOGGER.info("Session Destroyed! SessionId:{}", httpSessionEvent.getSession().getId());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
    }
}
