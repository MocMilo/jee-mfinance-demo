package com.infoshare.web.services.security.webfilters;

import com.infoshare.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "userView")
public class UserView implements Filter {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserView.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI();
        LOGGER.info("login filter request path: {}", path);

        if (req.getSession().getAttribute("authenticatedUser") != null) {
            User user = (User) req.getSession().getAttribute("authenticatedUser");

            if (user.getAdmin() == false) {
                filterChain.doFilter(servletRequest, servletResponse);
              //  LOGGER.info("User access verification success. Role isAdmin:{} ", user.getAdmin());
            } else {
                req.getRequestDispatcher("/auth/accessdenied.jsp").forward(req, resp);
                LOGGER.warn("User access verification failure! Role isAdmin:{} ", user.getAdmin());
            }
        } else {
            req.getRequestDispatcher("/auth/accessdenied.jsp").forward(req, resp);
            LOGGER.warn("User access verification failure!");
        }
    }

    @Override
    public void destroy() {

    }

}
