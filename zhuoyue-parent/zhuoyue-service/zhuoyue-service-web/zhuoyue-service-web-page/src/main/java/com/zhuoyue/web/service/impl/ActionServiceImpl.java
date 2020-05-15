package com.zhuoyue.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhuoyue.article.feign.ArticleFeignClient;
import com.zhuoyue.article.pojo.Article;
import com.zhuoyue.user.feign.UserFeignClient;
import com.zhuoyue.user.pojo.User;
import com.zhuoyue.web.exception.AccountPasswordException;
import com.zhuoyue.web.service.ActionService;
import entity.JwtUtil;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Linmo
 * @create 2020/4/24 20:25
 */
@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private ArticleFeignClient articleFeignClient;

    @Override
    public void login(HttpServletResponse response, HttpSession session, String username, String password, String remember) {
        //调用登陆接口
        Result<String> login = userFeignClient.login(username, password);
        if (login.getCode() != StatusCode.OK)
            throw new AccountPasswordException();
        //登陆成功设置cookie
        String jwt = login.getData();
        if (!StringUtils.isEmpty(remember) && remember.equals("on")) {
            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        //将用户信息设置到session
        try {
            Jws<Claims> parser = JwtUtil.parser(jwt);
            Object user = parser.getBody().get("User");
            User user1 = JSON.parseObject(JSON.toJSONString(user), User.class);
            session.setAttribute("user", user1);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean docSave(String title, String doc, String son, Integer sid, Long userId) {
        Article article = new Article();
        article.setContent(doc);
        article.setPlateName(son);
        article.setPalteId(sid);
        article.setTitle(title);
        article.setUserId(userId);
        Result add = articleFeignClient.add(article);
        return add.getCode() == StatusCode.OK;
    }

    @Override
    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        //清除session
        Object user = session.getAttribute("user");
        if (user != null) {
            session.removeAttribute("user");
        }
        //清除cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
