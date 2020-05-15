package com.zhuoyue.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Linmo
 * @create 2020/4/24 20:25
 */
public interface ActionService {
    void login(HttpServletResponse response, HttpSession session,
               String username, String password, String remember);

    boolean docSave(String title, String doc, String son, Integer sid, Long userId);

    void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response);
}
