package com.infoshare.web.user;

import com.infoshare.web.utils.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/userview/usermenu")
public class UserMenuServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
        resp.sendRedirect("usermenu.jsp");
        LOGGER.info("user from session:{}", user.getLogin());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
        resp.sendRedirect("usermenu.jsp");
        LOGGER.info("user from session:{}", user.getLogin());
    }
}