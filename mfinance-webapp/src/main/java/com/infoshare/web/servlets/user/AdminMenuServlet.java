package com.infoshare.web.servlets.user;

import com.infoshare.web.model.User;
import com.infoshare.web.utils.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/adminview/adminmenu")
public class AdminMenuServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminMenuServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
        resp.sendRedirect("adminMenu.jsp");
        LOGGER.info("User from session:{}", user.getLogin());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
        resp.sendRedirect("adminMenu.jsp");
        LOGGER.info("User from session:{}", user.getLogin());
    }

}


