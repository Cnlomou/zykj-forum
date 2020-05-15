package com.zhuoyue.getway.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Linmo
 * @create 2020/4/18 15:55
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

}
