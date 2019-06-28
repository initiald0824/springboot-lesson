package com.spark.springbootlesson.controller;

import com.spark.springbootlesson.entity.UserEntity;
import com.spark.springbootlesson.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author initiald0824
 * @date 2019/6/28 16:52
 */
@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserJpa userJpa;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> list() {
        return userJpa.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public UserEntity save(UserEntity entity) {
        return userJpa.save(entity);
    }

}
