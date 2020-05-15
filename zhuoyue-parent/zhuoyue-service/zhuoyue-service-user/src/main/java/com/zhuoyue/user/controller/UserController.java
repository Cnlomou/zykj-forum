package com.zhuoyue.user.controller;

import com.zhuoyue.user.pojo.User;
import com.zhuoyue.user.service.UserService;
import entity.BCrypt;
import entity.IdWorker;
import entity.Result;
import entity.StatusCode;

import java.util.List;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private IdWorker idWorker;

    @PutMapping("/increment/{id}")
    public Result updateArticleCount(@PathVariable(name = "id") Long id) {
        if (userService.incrementArticleCount(id))
            return new Result(true, StatusCode.OK, "跟新文章数成功");
        return new Result(false, StatusCode.ERROR, "跟新文章数失败");
    }

    @PostMapping("/search")
    public Result<List<User>> findByInfo(User user) {
        return new Result<>(true, StatusCode.OK, "按信息查询成功", userService.findByInfo(user));
    }

    @PostMapping("/login")
    public Result<String> login(String username, String password) {
        String login = null;
        try {
            login = userService.login(username, password);
            return new Result<>(true, StatusCode.OK, "登陆成功", login);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return new Result<>(true, StatusCode.LOGINERROR, "账号或者密码错误");
    }

    @DeleteMapping("/logout")
    public Result logout(String jwt) {
        userService.logout(jwt);
        return new Result(true, StatusCode.OK, "退出成功");
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<User>> findAllByPage(User user,
                                                @PathVariable(name = "pageNum") Integer num,
                                                @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "按信息查询并分页成功", userService.findInfoByPage(user, num, size));
    }

    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<User>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                                @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "查询所有并分页成功", userService.findAllByPage(num, size));
    }

    @PostMapping
    public Result add(User user) {
        user.setId(idWorker.nextId());
        userService.add(user);
        return new Result<>(true, StatusCode.OK, "添加数据成功");
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable(name = "id") Long id) {
        userService.delById(id);
        return new Result<>(true, StatusCode.OK, "删除数据成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Long id, User user) {
        user.setId(id);
        userService.update(user);
        return new Result<>(true, StatusCode.OK, "修改数据成功");
    }

    @GetMapping("/search/{id}")
    public Result<User> findById(@PathVariable(name = "id") Long id) {
        return new Result<>(true, StatusCode.OK, "按主键查找成功", userService.findById(id));
    }

    @GetMapping("/search")
    public Result<List<User>> findAll() {
        return new Result<>(true, StatusCode.OK, "查找所有数据成功", userService.findAll());
    }
}