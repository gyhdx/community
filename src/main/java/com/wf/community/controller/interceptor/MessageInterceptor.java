package com.wf.community.controller.interceptor;

import com.wf.community.entity.User;
import com.wf.community.service.MessageService;
import com.wf.community.util.HostHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/21 16:25
 */
@Component
public class MessageInterceptor implements HandlerInterceptor {

    @Resource
    private HostHolder hostHolder;

    @Resource
    private MessageService messageService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null) {
            int letterUnderCount = messageService.findLetterUnreadCount(user.getId(), null);
            int noticeUnderCount = messageService.findNoticeUnreadCount(user.getId(), null);
            modelAndView.addObject("allUnreadCount", letterUnderCount + noticeUnderCount);
        }
    }
}
