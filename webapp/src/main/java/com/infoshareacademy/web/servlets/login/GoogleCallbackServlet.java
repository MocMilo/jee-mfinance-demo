package com.infoshareacademy.web.servlets.login;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.infoshareacademy.web.model.user.GoogleUserProfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.services.persistence.user.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@WebServlet(urlPatterns = "callback")
public class GoogleCallbackServlet extends HttpServlet {

    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleCallbackServlet.class);

    private static final String OAUTH_SERVICE = "OAuth20Service";
    private static final String SECRET_STATE = "secretState";
    private static final String AUTH_USER = "authenticatedUser";
    private static final String STATE = "state";
    private static final String CODE = "code";

    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String code = req.getParameter(CODE);
        final String secretState = req.getParameter(STATE);
        final String value = (String) req.getSession().getAttribute(SECRET_STATE);

        if (!secretState.equals(value)) {
            LOGGER.error("Secret state values don't match! Expected:{}, got:{}", secretState, value);
            resp.sendRedirect("/index.html");
        }
        OAuth20Service service = (OAuth20Service) req.getSession()
                .getAttribute(OAUTH_SERVICE);
        try {
            OAuth2AccessToken accessToken = service.getAccessToken(code);
            accessToken = service.refreshAccessToken(accessToken.getRefreshToken());

            final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
            service.signRequest(accessToken, request);
            final Response response = service.execute(request);

            if (response.getCode() < 200 && response.getCode() > 299) {
                LOGGER.error("Problem in accessing Google user profile:{}" + response.getCode());
                resp.sendRedirect("/index.html");
            }
            GoogleUserProfile googleUserProfile = new ObjectMapper()
                    .readValue(response.getBody(), GoogleUserProfile.class);

            userService.authorize(googleUserProfile.getEmail(), req);
            this.userRedirection(req, resp);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void userRedirection(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute(AUTH_USER);
            LOGGER.info("UserAuthenticated: Id:{} login:{} role isAdmin:{}", user.getId(), user.getLogin(), user.getAdmin());

            if (!user.getAdmin()) {
                req.getRequestDispatcher("main").forward(req, resp);
            } else if (user.getAdmin()) {
                req.getRequestDispatcher("admin/panel").forward(req, resp);
            } else {
                req.getRequestDispatcher("accessdenied.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}




