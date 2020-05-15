package com.changyue.oauth.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.List;
import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/17 19:47
 */
public interface UserService {

    Map login(String username, String password, ClientDetails clientDetails, String grantType);

    void register(String username, String password, List<String> rules, UserDetailsManager userDetailsManager);
}
