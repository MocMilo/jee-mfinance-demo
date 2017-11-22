package com.infoshare.web.services.security.googleauthentication;

import com.infoshare.web.services.persistence.user.IUserService;
import com.infoshare.web.model.user.User;
import com.infoshare.web.services.persistence.user.activityreport.IUserActivityService;
import com.infoshare.web.services.persistence.user.activityreport.UserActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "authentication")
public class LoginServletGoogle extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServletGoogle.class);
    private final String AUTH_USER = "authenticatedUser";
    private final String ID_TOKEN = "id_token";
    private final String GOOGLE_USER_NAME = "name";
    private final String USER_ACTIVITY_LOGIN = "menu logged into system";

    @Inject
    IUserService userService;
    @Inject
    IUserActivityService userActivityService;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");

        try {
            //fixme
            // google auth turned off
            /*            String idToken = req.getParameter(ID_TOKEN);
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get(GOOGLE_USER_NAME);
            String email = payLoad.getEmail();*/

            String email = "dmmtest441@gmail.com";

            User user = userDBaseProcessing(email);
            user.setLastLoginDateTime(LocalDateTime.now());
            userService.update(user);
            HttpSession session = req.getSession(true);
            session.setAttribute(AUTH_USER, user);

            userActivityService.saveActivity(new UserActivity(user.getLogin(), USER_ACTIVITY_LOGIN, req.getSession().getId()));

            LOGGER.info("UserAuthenticated: Id:{} login:{} role isAdmin:{}", user.getId(), user.getLogin(), user.getAdmin());

            userViewRedirection(user, req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private void userViewRedirection(User user, HttpServletRequest req, HttpServletResponse resp) {

        try {
            if (!user.getAdmin()) {
                LOGGER.info("User view redirection: isAdmin:{}", user.getAdmin());
                req.getRequestDispatcher("auth/userview/usermenu").forward(req, resp);
            } else if (user.getAdmin()) {
                LOGGER.info("User view redirection: isAdmin:{}", user.getAdmin());
                req.getRequestDispatcher("auth/adminview/adminmenu").forward(req, resp);
            } else {
                LOGGER.warn("User redirection failure.");
                req.getRequestDispatcher("auth/accessdenied.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private User userDBaseProcessing(String userEmail) {

        if (userService.getUserByEmail(userEmail).isEmpty()) {
            User user = new User();
            user.setLogin(userEmail);
            user.setAdmin(false);
            userService.add(user);
            return user;
        } else {
            return userService.getUserByEmail(userEmail).stream()
                    .findAny()
                    .orElseThrow(NullPointerException::new);
        }
    }

}
