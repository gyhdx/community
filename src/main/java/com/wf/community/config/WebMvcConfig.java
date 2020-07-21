package com.wf.community.config;

import com.wf.community.controller.interceptor.LoginRequiredInterceptor;
import com.wf.community.controller.interceptor.LoginticketInterceptor;
import com.wf.community.controller.interceptor.MessageInterceptor;
import com.wf.community.controller.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/15 14:36
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private MyInterceptor interceptor;

    @Resource
    private LoginticketInterceptor loginticketInterceptor;

    @Resource
    private LoginRequiredInterceptor loginRequiredInterceptor;

    @Resource
    private MessageInterceptor messageInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                //设置不被拦截器拦截的路径
                .excludePathPatterns("/**/*.css","/**/*.js" , "/**/*.png" , "/**/*.jpg" , "/**/*.jpeg")
                //配置要拦截的路径
                .addPathPatterns("/register" , "/login");

        registry.addInterceptor(loginticketInterceptor)
                .excludePathPatterns("/**/*.css","/**/*.js" , "/**/*.png" , "/**/*.jpg" , "/**/*.jpeg");

        registry.addInterceptor(loginRequiredInterceptor)
                .excludePathPatterns("/**/*.css","/**/*.js" , "/**/*.png" , "/**/*.jpg" , "/**/*.jpeg");

        registry.addInterceptor(messageInterceptor)
                .excludePathPatterns("/**/*.css","/**/*.js" , "/**/*.png" , "/**/*.jpg" , "/**/*.jpeg");
    }
}
