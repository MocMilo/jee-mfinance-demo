package com.infoshareacademy.web.servlets.administration;

import com.infoshareacademy.web.services.persistence.user.UserService;
import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.utils.constants.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/user")
public class UserDetailsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServlet.class);

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String editId = req.getParameter("id");
        long Id = Long.parseLong(editId);
        User user = userService.get(Id);

        req.setAttribute(ConstantsProvider.CONTENT_WRAPPER, user);
        req.getRequestDispatcher("/admin/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isAdmin = req.getParameter("isAdmin") != null;
        User user = userService.get(Long.parseLong(req.getParameter("id")));
        if (user != null) {
            user.setAdmin(isAdmin);
            userService.update(user);
        } else {
            LOGGER.error("Failed to update user. User doesn't exists.");
            throw new NullPointerException();
        }
        req.setAttribute(ConstantsProvider.ALL_USERS, userService.getAllUsers());
        req.getRequestDispatcher("/admin/users").forward(req, resp);
    }
}
