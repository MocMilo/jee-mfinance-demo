package com.infoshareacademy.web.servlets.login;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.infoshareacademy.web.services.webconfiguration.WebConfigurationService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.*;


@WebServlet(urlPatterns = "login")
public class GoogleLoginServlet extends HttpServlet {
    @Inject
    private WebConfigurationService webConfig;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String secretState = SECRET + new Random().nextInt(999_999);
        final OAuth20Service service = new ServiceBuilder(webConfig.get().getClientId())
                .apiSecret(webConfig.get().getClientSecret())
                .scope(SCOPE_PROFILE)
                .scope(SCOPE_EMAIL)
                .state(secretState)
                .callback(webConfig.get().getCallbackURL())
                .build(GoogleApi20.instance());

        HttpSession session = req.getSession(true);
        session.setAttribute(OAUTH_SERVICE, service);
        session.setAttribute(SECRET_STATE, secretState);

        final Map<String, String> additionalParams = new HashMap<>();
        additionalParams.put(ACCESS_TYPE, OFFLINE);
        additionalParams.put(PROMPT, CONSENT);

        final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        resp.sendRedirect(authorizationUrl);
    }
}
