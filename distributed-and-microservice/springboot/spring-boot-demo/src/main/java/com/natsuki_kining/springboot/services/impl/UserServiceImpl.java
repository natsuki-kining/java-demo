package com.natsuki_kining.springboot.services.impl;

import com.natsuki_kining.springboot.dao.entity.User;
import com.natsuki_kining.springboot.dao.mappers.UserMapper;
import com.natsuki_kining.springboot.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserMapper userMapper;  //代理对象

    @Override
    public int insert(User user) {
        //TODO 业务逻辑
        return userMapper.insert(user);
    }
}
