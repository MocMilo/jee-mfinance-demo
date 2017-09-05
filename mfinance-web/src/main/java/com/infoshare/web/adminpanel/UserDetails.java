package com.infoshare.web.adminpanel;

import com.infoshare.web.user.IUserService;
import com.infoshare.web.user.User;
import com.infoshare.web.utils.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth/adminview/userdetails")
public class UserDetails extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetails.class);
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
            LOGGER.error("Failed to update user. User doesn't exists.");
            throw new NullPointerException();
        }

        req.setAttribute(ConstantsProvider.ALL_USERS, userService.getAllUsers());
        req.getRequestDispatcher("../adminview/userManagement.jsp").forward(req, resp);
    }
}
