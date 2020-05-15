package com.zhuoyue.web.controller;

import com.zhuoyue.user.pojo.User;
import com.zhuoyue.web.exception.AccountPasswordException;
import com.zhuoyue.web.service.ActionService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Linmo
 * @create 2020/4/24 20:24
 */
@RestController
@CrossOrigin
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping("/user/dosave")
    public Result dosave(@RequestParam(name = "editor-markdown-doc") @NotEmpty String editorDoc, @NotEmpty String title,
                         @NotEmpty String son, @NotNull Integer sid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (actionService.docSave(title, editorDoc, son, sid, user.getId()))
            return new Result(true, StatusCode.OK, "发帖成功");
        return new Result(false, StatusCode.ERROR, "发帖失败");
    }

    @PostMapping("/user/dologin")
    public Result<String> login(HttpServletResponse response, HttpSession session,
                                String username, String password, String remember) {
        try {
            actionService.login(response, session, username, password, remember);
        } catch (AccountPasswordException e) {
            return new Result<>(false, StatusCode.LOGINERROR, "账号或者密码错误");
        }

        return new Result<>(true, StatusCode.OK, "登陆成功");
    }

    @GetMapping("/user/dologout")
    public Result<String> logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        actionService.logout(session, request, response);
        return new Result<>(true, StatusCode.OK, "退出登陆");
    }
}
