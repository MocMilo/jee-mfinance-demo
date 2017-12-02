package com.infoshareacademy.web.servlets.login;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

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

@WebServlet(urlPatterns = "login")
public class GoogleLoginServlet extends HttpServlet {
    private final static String CLIENT_ID = "122677138693-qfl08kemnppv52asvi3s7tra2dptn2mr.apps.googleusercontent.com";
    private final static String CLIENT_SECRET = "7wVONyV2eLAyegLFJvip3Lp3";
    private static final String OAUTH_SERVICE = "OAuth20Service";
    private static final String SECRET_STATE = "secretState";
    private final static String SECRET = "secret";

    private final static String SCOPE_PROFILE = "profile";
    private final static String SCOPE_EMAIL = "email";
    private final static String CALLBACK_URL = "http://localhost:8080/callback";

    private final static String ACCESS_TYPE = "access_type";
    private final static String OFFLINE = "offline";
    private final static String PROMPT = "prompt";
    private final static String CONSENT = "consent";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String secretState = SECRET + new Random().nextInt(999_999);
        final OAuth20Service service = new ServiceBuilder(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .scope(SCOPE_PROFILE)
                .scope(SCOPE_EMAIL)
                .state(secretState)
                .callback(CALLBACK_URL)
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
