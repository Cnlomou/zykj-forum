package com.zhuoyue.user.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

import com.zhuoyue.user.pojo.User;

public interface UserService {
    boolean incrementArticleCount(Long id);

    String login(String username, String password);

    void logout(String jwt);

    void add(User user);

    void delById(Long id);

    void update(User user);

    User findById(Long id);

    List<User> findAll();

    PageInfo<User> findAllByPage(Integer pageNum, Integer pageSize);

    List<User> findByInfo(User user);

    PageInfo<User> findInfoByPage(User user, Integer pageNum, Integer pageSize);
}