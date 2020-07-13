package com.wf.community.service.impl;

import com.wf.community.dao.UserMapper;
import com.wf.community.entity.User;
import com.wf.community.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserById(int userId){
        return userMapper.selectById(userId);
    }
}
