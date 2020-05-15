package com.zhuoyue.web.conf;

import com.zhuoyue.article.feign.PlateFeignClient;
import com.zhuoyue.article.pojo.PlatePair;
import com.zhuoyue.web.controller.ViewController;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author Linmo
 * @create 2020/4/24 19:51
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    PlateFeignClient plateFeignClient;
    List<PlatePair> data = null;
    Object lock = new Object();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                HttpSession session = request.getSession();
                if (handler instanceof HandlerMethod) {
                    Object user = session.getAttribute("user");
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    if (handlerMethod.getBeanType().equals(ViewController.class)) {
                        //设置用户信息
                        if (user != null && modelAndView != null)
                            modelAndView.addObject("user", user);


                        //设置推荐板块
                        List<PlatePair> data = null;
                        synchronized (lock) {
                            if (WebConfiguration.this.data == null) {
                                Result<List<PlatePair>> listResult = plateFeignClient.recommendPlate();
                                if (listResult.getCode() != StatusCode.OK)
                                    return;
                                data = listResult.getData();
                                WebConfiguration.this.data = data;

                            }
                        }
                        modelAndView.addObject("recommend", data == null ? WebConfiguration.this.data : data);
                    }

                }
            }
        });
    }


    /**
     * 添加回复用户登陆态的filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<LoginJwtFilter> loginJwtFilterFilterRegistrationBean() {
        FilterRegistrationBean<LoginJwtFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginJwtFilter());
        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthoricationFilter> authoricationFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthoricationFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new AuthoricationFilter());
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/edit"));
        return filterFilterRegistrationBean;
    }
}
