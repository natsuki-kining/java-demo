package com.natsuki_kining.spring.cloud.and.docker2.microservice.simple.provider.user.my.metadata.repository;

import com.natsuki_kining.spring.cloud.and.docker2.microservice.simple.provider.user.my.metadata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}