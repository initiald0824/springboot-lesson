package com.spark.springbootlesson.jpa;

import com.spark.springbootlesson.entity.UserEntity;
import com.spark.springbootlesson.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author initiald0824
 * @date 2019/7/3 17:03
 */
public interface UsersJpa extends JpaRepository<UsersEntity, Long> {
    public UsersEntity findByUsername(String username);
}
