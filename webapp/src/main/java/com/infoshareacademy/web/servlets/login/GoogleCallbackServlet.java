package com.infoshareacademy.web.servlets.login;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.infoshareacademy.web.model.session.SessionContainer;
import com.infoshareacademy.web.model.user.GoogleUserProfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.services.persistence.user.UserService;

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

import static com.infoshareacademy.web.utils.constants.ConstantsProvider.*;

@WebServlet(urlPatterns = "callback")
public class GoogleCallbackServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleCallbackServlet.class);
    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
    @Inject
    private UserService userService;
    @Inject
    private SessionContainer sessionContainer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String code = req.getParameter(CODE);
        final String secretState = req.getParameter(STATE);
        final String value = (String) req.getSession().getAttribute(SECRET_STATE);
        if (!secretState.equals(value)) {
            resp.sendRedirect("accessdenied.jsp");
            LOGGER.error("Failed to match secret state values (expected:{}, received:{}).",
                    secretState, value);
        }
        OAuth20Service service = (OAuth20Service) req.getSession()
                .getAttribute(OAUTH_SERVICE);
        try {
            OAuth2AccessToken accessToken = service.getAccessToken(code);
            accessToken = service.refreshAccessToken(accessToken.getRefreshToken());

            final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
            service.signRequest(accessToken, request);
            final Response response = service.execute(request);

            if (response.getCode() < 200 || response.getCode() > 299) {
                resp.sendRedirect("accessdenied.jsp");
                LOGGER.error("Failed to access Google user profile:{}" + response.getCode());
            }
            final GoogleUserProfile googleUserProfile = new ObjectMapper()
                    .readValue(response.getBody(), GoogleUserProfile.class);

            userService.authorize(googleUserProfile.getEmail(), req);
            this.userRedirection(req, resp);
        } catch (InterruptedException | ExecutionException e) {
            resp.sendRedirect("accessdenied.jsp");
            LOGGER.error("Failed process callback authentication request {}", e.getMessage());
        }
    }

    private void userRedirection(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            final User user = sessionContainer.getUser();
            LOGGER.info("UserAuthenticated: Id:{} login:{} role isAdmin:{}", user.getId(), user.getLogin(), user.getAdmin());
            if (!user.getAdmin()) {
                req.getRequestDispatcher("main").forward(req, resp);
            } else if (user.getAdmin()) {
                req.getRequestDispatcher("admin/panel").forward(req, resp);
            } else {
                req.getRequestDispatcher("accessdenied.jsp").forward(req, resp);
            }
        } catch (ServletException e) {
            resp.sendRedirect("accessdenied.jsp");
            LOGGER.error("Failed process callback authentication request {}", e.getMessage());
        }
    }
}




