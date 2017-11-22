package com.infoshare.web.servlets.administration;

import com.infoshare.web.services.persistence.user.IUserService;
import com.infoshare.web.model.user.User;
import com.infoshare.web.utils.constants.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth/adminview/userdetails")
public class UserDetailsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServlet.class);
    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String editId = req.getParameter("id");
        long Id = Long.parseLong(editId);
        User user = userService.get(Id);

        req.setAttribute(ConstantsProvider.CONTENT_WRAPPER, user);
        req.getRequestDispatcher("../adminview/userDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isAdmin = req.getParameter("isAdmin") != null;

        User user = userService.get(Long.parseLong(req.getParameter("id")));
        if(user!=null) {
            user.setAdmin(isAdmin);
            userService.update(user);
        }
        else {
            LOGGER.error("Failed to update menu. User doesn't exists.");
            throw new NullPointerException();
        }

        req.setAttribute(ConstantsProvider.ALL_USERS, userService.getAllUsers());
        req.getRequestDispatcher("../adminview/userManagement.jsp").forward(req, resp);
    }
}
