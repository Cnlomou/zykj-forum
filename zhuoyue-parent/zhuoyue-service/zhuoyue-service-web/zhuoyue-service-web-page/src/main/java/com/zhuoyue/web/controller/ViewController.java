package com.zhuoyue.web.controller;

import com.zhuoyue.user.feign.UserFeignClient;
import com.zhuoyue.web.bean.EditBean;
import com.zhuoyue.web.service.ViewPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @author Linmo
 * @create 2020/4/23 17:18
 */
@Controller
@CrossOrigin
public class ViewController {

    @Autowired
    private ViewPageService viewPageService;


    @RequestMapping({"/", "/index"})
    String Index(Model model, String msg) {
        model.addAttribute("msg", msg);
        return "index";
    }

    @RequestMapping("/forum")
    String Fourm() {
        return "forum";
    }

    @RequestMapping("/active")
    String Active() {
        return "active";
    }

    @GetMapping("/section")
    String section(Model model) {
        viewPageService.buildSection(model);
        return "section";
    }

    @GetMapping("/forum-section/{num}")
    String formSection(Integer id, String par, String son, Model model,
                       @PathVariable(name = "num") Integer num) {
        if (id == null || StringUtils.isEmpty(par) || StringUtils.isEmpty(son))
            return "error/404";
        viewPageService.buildForumSectionPage(model, id, par, son, num, 5);
        return "forum-section";
    }

    @GetMapping("/userinfo")
    String userInfo() {
        return "userinfo";
    }

    @GetMapping("/edit")
    String editPage(@Valid EditBean editBean, Model model) {
        model.addAttribute("edit", editBean);
        return "edit";
    }

}
