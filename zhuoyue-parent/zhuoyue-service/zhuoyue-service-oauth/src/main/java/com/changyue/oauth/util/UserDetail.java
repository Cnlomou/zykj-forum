package com.changyue.oauth.util;

import org.apache.http.util.Asserts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

/**
 * @author Linmo
 * @create 2020/4/21 15:23
 */
public class UserDetail {
    private static final String PASSWORD_PRE = "{noop}";
    private static final String ROLE_PRE = "ROLE_";

    public static UserDetails build(String username, String password, Collection<String> roles) {
        return buildDefault(username, password, roles, ROLE_PRE, false);
    }

    public static UserDetails buildByBcrpy(String username, String password, Collection<String> roles) {
        return buildDefault(username, password, roles, "", true);
    }

    static UserDetails buildDefault(String username, String password, Collection<String> roles, String passPre, boolean encode) {
        Asserts.notNull(username, "username");
        Asserts.notNull(password, "password");
        Asserts.notNull(roles, "roles");
        if (encode)
            password = new BCryptPasswordEncoder().encode(password);
        Set<GrantedAuthority> roles_set = new HashSet<>();
        for (String s : roles) {
            roles_set.add(new SimpleGrantedAuthority(ROLE_PRE + s.toUpperCase()));
        }
        return new User(username, passPre + password, roles_set);
    }
}
