package com.spark.springbootlesson.jpa;

import com.spark.springbootlesson.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @author initiald0824
 * @date 2019/6/28 17:06
 */
public interface UserJpa extends JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity>, Serializable {
}
