package com.spark.springbootlesson.jpa;

import com.spark.springbootlesson.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author initiald0824
 * @date 2019/6/28 17:06
 */
@Transactional(rollbackFor = Exception.class)
public interface UserJpa extends JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity>, Serializable {

    @Query(value = "select * from t_user where t_age > ?1", nativeQuery = true)
    public List<UserEntity> nativeQuery(int age);

    @Modifying
    @Query(value = "delete from t_user where t_name = ?1 and t_pwd = ?2", nativeQuery = true)
    public void deleteQuery(String name, String pwd);
}
