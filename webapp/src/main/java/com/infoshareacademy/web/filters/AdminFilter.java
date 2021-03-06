package com.infoshareacademy.web.filters;

import com.infoshareacademy.web.model.session.SessionContainer;
import com.infoshareacademy.web.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "adminFilter")
public class AdminFilter extends AuthenticationFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminFilter.class);
    @Inject
    private SessionContainer sessionContainer;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = sessionContainer.getUser();
        if (user.getAdmin()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            req.getRequestDispatcher("/accessdenied.jsp").forward(req, resp);
            LOGGER.warn("User authorization rejected! Role isAdmin:{} ", user.getAdmin());
        }
    }

    @Override
    public void destroy() {
    }
}
