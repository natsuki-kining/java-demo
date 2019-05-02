package com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.repository;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

/**
 * 用户仓库
 * sql、nosql、内存类型
 */
@Repository
public class UserRepository {

    private final DataSource dataSource;

    private final DataSource masterDataSource;

    private final DataSource slaveDataSource;

    public UserRepository(DataSource dataSource,
                          @Qualifier("masterDataSource") DataSource masterDataSource,
                          @Qualifier("slaveDataSource") DataSource slaveDataSource){
        this.dataSource = dataSource;
        this.masterDataSource = masterDataSource;
        this.slaveDataSource = slaveDataSource;
    }

    public boolean save(User user){
        System.out.printf("Thread : %s ;save user:%s",Thread.currentThread().getName(),user);

        boolean success = false;
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name) value(?)");
            preparedStatement.setString(1,user.getName());
            success = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    public Collection<User> findAll(){
        return Collections.emptyList();
    }

}
