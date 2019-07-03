package com.spark.springbootlesson.service;

import com.spark.springbootlesson.entity.UserEntity;
import com.spark.springbootlesson.entity.UsersEntity;
import com.spark.springbootlesson.jpa.UsersJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author initiald0824
 * @date 2019/7/3 17:05
 */
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersJpa usersJpa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersJpa.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("未查询到用户: " + username + "信息！");
        }
        return user;
    }
}
