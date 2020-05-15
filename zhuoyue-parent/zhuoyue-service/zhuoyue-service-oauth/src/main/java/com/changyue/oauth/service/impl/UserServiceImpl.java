package com.changyue.oauth.service.impl;

import com.changyue.oauth.service.UserService;
import com.changyue.oauth.util.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/17 19:49
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Map login(String username, String password, ClientDetails clientDetails, String grantType) {

        //将clientdetails编码
        Base64.Encoder encoder = Base64.getEncoder();
        String clientSecret = clientDetails.getClientSecret();
        clientSecret = clientSecret.substring(clientSecret.lastIndexOf('}') + 1);
        byte[] encode = encoder.encode((clientDetails.getClientId() + ":" + clientSecret).getBytes());

        //封装请求参数
        MultiValueMap<String, String> bodys = new LinkedMultiValueMap<>();
        bodys.add("username", username);
        bodys.add("password", password);
        bodys.add("grant_type", "password");
        //构建请求头
        MultiValueMap<String, String> heads = new LinkedMultiValueMap<>();
        heads.add("Authorization", "Basic " + new String(encode));
        //调用
        HttpEntity entity = new HttpEntity(bodys, heads);
        Map map = restTemplate.postForObject(buildRequestUrl(), entity, Map.class);
        return map;
    }

    /**
     * 注册用户授权认证信息
     *
     * @param username
     * @param password
     * @param rules
     * @param userDetailsManager
     */
    @Override
    public void register(String username, String password, List<String> rules, UserDetailsManager userDetailsManager) {
        userDetailsManager.createUser(UserDetail.buildByBcrpy(username, password, rules));
    }

    private String buildRequestUrl() {
        ServiceInstance choose = loadBalancerClient.choose("user-oauth");
        return choose.getUri().toString() + "/oauth/token";
    }

}
