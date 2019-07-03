package com.spark.springbootlesson.service;

import com.spark.springbootlesson.entity.UserEntity;
import com.spark.springbootlesson.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author initiald0824
 * @date 2019/7/3 15:40
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    @Autowired
    private UserJpa userJpa;

    @Cacheable
    public List<UserEntity> list()  {
        return userJpa.findAll();
    }
}
