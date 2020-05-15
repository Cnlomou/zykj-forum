package com.changyue.oauth.controller;

import com.changyue.oauth.config.ClientDetail;
import com.changyue.oauth.service.UserService;
import com.changyue.oauth.util.AccessToken;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.lang.Assert;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.security.PermitAll;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/17 19:44
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(path = "/signin", method = RequestMethod.POST)
    public Result<Map> sigin(String username, String password) {
        ClientDetailsService bean = applicationContext.getBean(ClientDetailsService.class);
        ClientDetails oauth2 = bean.loadClientByClientId("oauth2");
        Assert.notNull(oauth2, "没有oauth2这个clientid");
        Map password1 = null;
        try {
            password1 = userService.login(username, password, oauth2, "password");
        } catch (Exception ignored) {
            if (logger.isWarnEnabled())
                logger.warn(ignored.getMessage());
            return new Result<>(false, StatusCode.LOGINERROR, "账号或密码错误");
        }
        Assert.notNull(password, "服务端异常");
        return new Result<>(true, StatusCode.OK, "登陆成功", password1);
    }

    @RequestMapping(path = "signup", method = RequestMethod.POST)
    public Result logout(String username, String password, @RequestParam(name = "roles") String[] roles) {
        UserDetailsManager bean = applicationContext.getBean(UserDetailsManager.class);
        userService.register(username, password, Arrays.asList(roles), bean);
        return new Result(true, StatusCode.OK, "更新用户授权认证信息");
    }
}
