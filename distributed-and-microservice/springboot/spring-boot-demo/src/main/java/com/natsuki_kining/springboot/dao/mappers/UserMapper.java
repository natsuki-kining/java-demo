package com.natsuki_kining.springboot.dao.mappers;

import com.natsuki_kining.springboot.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insert(User user);
}
