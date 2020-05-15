package com.zhuoyue.web.service;

import com.zhuoyue.web.bean.EditBean;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/23 18:09
 */
public interface ViewPageService {
    void buildSection(Model model);

    void buildForumSectionPage(Model model, Integer id, String par, String son, Integer num, Integer size);
}
