package com.natsuki_kining.springboot.persistence;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insert(User user);

}
