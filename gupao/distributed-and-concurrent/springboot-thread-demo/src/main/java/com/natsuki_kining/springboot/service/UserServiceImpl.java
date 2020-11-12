package com.natsuki_kining.springboot.service;

import com.natsuki_kining.springboot.persistence.User;
import com.natsuki_kining.springboot.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
