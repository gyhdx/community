package com.wf.community.controller.interceptor;

import com.wf.community.annotation.LoginRequired;
import com.wf.community.util.HostHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/15 16:16
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Resource
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            Method method1 = method.getMethod();
            LoginRequired annotation = method1.getAnnotation(LoginRequired.class);
            if (annotation != null && hostHolder.getUser() == null){
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        }
        return true;
    }
}
