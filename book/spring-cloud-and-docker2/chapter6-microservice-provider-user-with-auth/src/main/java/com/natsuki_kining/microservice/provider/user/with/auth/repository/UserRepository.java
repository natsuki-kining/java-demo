package com.natsuki_kining.microservice.provider.user.with.auth.repository;

import com.natsuki_kining.microservice.provider.user.with.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}