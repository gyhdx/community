package com.wf.community.service;

import com.wf.community.entity.LoginTicket;
import com.wf.community.entity.User;

import java.util.Map;

public interface UserService {

    User findUserById(int userId);

    Map<String, Object> register(User user);

    int activation(int userId, String code);

    Map<String, Object> login(String username, String password, int expiredSeconds);

    void logout(String ticket);

    LoginTicket findLoginTicket(String ticket);

    int updateHeader(int userId, String headerUrl);

    User findUserByName(String username);
}
