package com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.repository;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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

    private final JdbcTemplate jdbcTemplate;

    private final PlatformTransactionManager platformTransactionManager;

    public UserRepository(DataSource dataSource,
                          @Qualifier("masterDataSource") DataSource masterDataSource,
                          @Qualifier("slaveDataSource") DataSource slaveDataSource,
                          JdbcTemplate jdbcTemplate,
                          PlatformTransactionManager platformTransactionManager){
        this.dataSource = dataSource;
        this.masterDataSource = masterDataSource;
        this.slaveDataSource = slaveDataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }

    private boolean jdbcSave(User user){
        boolean success = false;
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name) value(?)");
            preparedStatement.setString(1,user.getName());
            success = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.commit();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    @Transactional
    public boolean transactionalSave(User user){
        boolean success = jdbcTemplate.execute("insert into users(name) value(?)", new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setString(1, user.getName());
                return preparedStatement.executeUpdate() > 0;
            }
        });
        return success;
    }


    public boolean save(User user){
        System.out.printf("Thread : %s ;save user:%s",Thread.currentThread().getName(),user);

        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

        boolean success = false;
        try{
            success = jdbcTemplate.execute("insert into users(name) value(?)", new PreparedStatementCallback<Boolean>() {
                @Override
                public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                    preparedStatement.setString(1, user.getName());
                    return preparedStatement.executeUpdate() > 0;
                }
            });
            platformTransactionManager.commit(transactionStatus);
        }catch (Exception e){
            e.printStackTrace();
            platformTransactionManager.rollback(transactionStatus);
        }
        return success;
    }

    public Collection<User> findAll(){
        return Collections.emptyList();
    }

}
