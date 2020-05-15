package com.zhuoyue.user.service.impl;

import com.zhuoyue.user.conf.RedisCacheJwtManager;
import com.zhuoyue.user.dao.UserMapper;
import com.zhuoyue.user.service.UserService;
import com.zhuoyue.user.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.BCrypt;
import entity.JwtUtil;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisCacheJwtManager<User> jwtManager;

    @Override
    public boolean incrementArticleCount(Long id) {
        User user1 = new User();
        user1.setId(id);
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            user1.setArtCount(user.getArtCount() + 1);
            return userMapper.updateByPrimaryKeySelective(user1) > 0;
        }
        return false;
    }

    @Override
    public String login(String username, String password) {
        //如果存在令牌，则尝试从缓存中读取
        if (jwtManager.containsPair(username, password)) {
            return jwtManager.getPairValue(username, password);
        }
        //不存在从数据库中读出
        User user = new User();
        user.setUsername(username);
        List<User> select = userMapper.select(user);
        User user1 = select.get(0);
        if (!BCrypt.checkpw(password, user1.getPassword()))
            throw new IllegalStateException();
        user1.setPassword(null);
        user1.setPhone(null);
        user1.setUsername(null);

        //设置到缓存
        String njwt = JwtUtil.buildJwt("jwt", user1);
        jwtManager.setPairValue(username, password, njwt);
        jwtManager.set(njwt, user1);
        return njwt;
    }

    @Override
    public void logout(String jwt) {
    }

    @Override
    public void add(User user) {
        user.setCreateTime(new Date());
        String password = user.getPassword();
        Assert.assertNotNull("password not is null", password);
        //密码加密
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        Random random = new Random();
        user.setName("zykj_" + random.nextInt());
        user.setPicUrl("http://47.94.170.1:8080/group1/M00/00/00/L16qAV6izKaAflJUAAAI-5tIShw118.jpg");
        userMapper.insertSelective(user);
    }

    @Override
    public void delById(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageInfo<User> findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public List<User> findByInfo(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (user.getName() != null) {
            criteria.andLike("name", "%" + user.getName() + "%");
        }
        if (user.getEmail() != null) {
            criteria.andLike("email", "%" + user.getEmail() + "%");
        }
        if (user.getPhone() != null) {
            criteria.andLike("phone", "%" + user.getPhone() + "%");
        }
        if (user.getStatus() != null) {
            criteria.andLike("status", "%" + user.getStatus() + "%");
        }
        if (user.getUsername() != null) {
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }
        if (user.getPassword() != null) {
            criteria.andLike("password", "%" + user.getPassword() + "%");
        }
        if (user.getPicUrl() != null) {
            criteria.andLike("picUrl", "%" + user.getPicUrl() + "%");
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public PageInfo<User> findInfoByPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findByInfo(user));
    }
}