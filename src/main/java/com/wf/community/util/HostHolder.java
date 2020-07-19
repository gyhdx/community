package com.wf.community.util;

import com.wf.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @Description TODO 持有用户信息，来代替session对象
 * @Author gyhdx
 * @Date 2020/7/15 15:07
 */
@Component
public class HostHolder {
    private ThreadLocal<User> userTL = new ThreadLocal<>();

    public void setUser(User user){
        userTL.set(user);
    }

    public User getUser(){
        return userTL.get();
    }

    public void clear(){
        userTL.remove();
    }
}
