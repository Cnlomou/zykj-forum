package com.zhuoyue.web.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/24 21:25
 */
@Configuration
public class CustomErrorResolver implements ErrorViewResolver {
    @Value("${index-page}")
    private String indePage;

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView();
        String referer = null;
        if (!StringUtils.isEmpty(indePage))
            referer = indePage;
        else
            referer = request.getHeader("Referer");
        if (status.is4xxClientError()) {
            modelAndView.setViewName("error/404");
            modelAndView.addObject("redir", referer);
        } else if (status.is5xxServerError()) {
            modelAndView.setViewName("error/500");
            modelAndView.addObject("redir", model);
        }
        return modelAndView;
    }
}
