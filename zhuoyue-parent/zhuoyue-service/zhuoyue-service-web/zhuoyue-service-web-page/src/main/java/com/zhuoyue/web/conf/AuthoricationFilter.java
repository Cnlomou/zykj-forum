package com.zhuoyue.web.conf;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Linmo
 * @create 2020/4/26 17:26
 */
@Order(5)
public class AuthoricationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
            HttpSession session = servletRequest1.getSession();
            Object user = session.getAttribute("user");
            if (user == null) {
                servletRequest1.getRequestDispatcher("/index?msg=请登陆再访问").forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
