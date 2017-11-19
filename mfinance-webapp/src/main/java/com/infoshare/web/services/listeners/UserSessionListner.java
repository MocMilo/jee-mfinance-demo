package com.infoshare.web.services.listeners;

import com.infoshare.web.model.User;
import com.infoshare.web.services.reports.activity.IUserActivityService;
import com.infoshare.web.services.reports.activity.UserActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.time.Instant;
import java.time.LocalDateTime;

import static com.infoshare.web.utils.ConstantsProvider.AUTH_USER;
import static java.time.ZoneOffset.UTC;

@WebListener
public class UserSessionListner implements HttpSessionListener, HttpSessionAttributeListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionListner.class);
    private final String USER_ACTIVITY_LOGOUT = "session destroyed";

    @Inject
    IUserActivityService userActivityService;

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        if (httpSessionBindingEvent.getSession().getAttribute(AUTH_USER) != null) {
            User user = (User) httpSessionBindingEvent.getSession().getAttribute(AUTH_USER);
            LOGGER.info("User added to session (login):{}", user.getLogin());
        } else {
            LOGGER.warn("Not authenticated user session opening attempt! {}",
                    httpSessionBindingEvent
                            .getSession()
                            .getServletContext()
                            .getContextPath());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        Instant instant = Instant.ofEpochMilli(httpSessionEvent.getSession().getCreationTime());
        LocalDateTime sessionCreated = LocalDateTime.ofInstant(instant, UTC);

        LOGGER.info("SessionId:{} Created:{}  ", httpSessionEvent.getSession().getId(), sessionCreated);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        userActivityService.saveActivity(new UserActivity("HttpSessionEventListener",
                USER_ACTIVITY_LOGOUT, httpSessionEvent.getSession().getId()));

        LOGGER.info("Session Distroyed! SessionId:{}", httpSessionEvent.getSession().getId());
    }
}
