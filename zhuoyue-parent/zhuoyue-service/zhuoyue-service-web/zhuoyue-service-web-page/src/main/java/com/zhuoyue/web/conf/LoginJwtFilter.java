package com.zhuoyue.web.conf;

import com.alibaba.fastjson.JSON;
import com.zhuoyue.user.pojo.User;
import entity.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Linmo
 * @create 2020/4/25 21:34
 * <p>
 * 利用jwt来恢复用户的登陆态
 */
@Order(1)
public class LoginJwtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpSession session = httpServletRequest.getSession();
            if (session.getAttribute("user") == null) {
                Cookie jwtCookie = getJwtCookie(httpServletRequest);
                if (jwtCookie != null)
                    setUserLoginStatus(jwtCookie, session);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Cookie getJwtCookie(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    return cookie;
                }
            }
        }
        return null;
    }

    private void setUserLoginStatus(Cookie cookie, HttpSession session) {
        try {
            Jws<Claims> parser = JwtUtil.parser(cookie.getValue());
            Object user = parser.getBody().get("User");
            User user1 = JSON.parseObject(JSON.toJSONString(user), User.class);
            session.setAttribute("user", user1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
