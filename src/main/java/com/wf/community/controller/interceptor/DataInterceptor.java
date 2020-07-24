package com.wf.community.controller.interceptor;

import com.wf.community.entity.User;
import com.wf.community.service.impl.DataService;
import com.wf.community.util.HostHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/22 23:45
 */
@Component
public class DataInterceptor implements HandlerInterceptor {

    @Resource
    private DataService dateService;

    @Resource
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 统计UV
        String ip = request.getRemoteHost();
        dateService.recordUV(ip);

        //统计DAU
        User user = hostHolder.getUser();
        if (user != null){
            dateService.recordDAU(user.getId());
        }
        return true;
    }
}
