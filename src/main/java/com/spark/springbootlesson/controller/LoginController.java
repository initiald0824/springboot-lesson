package com.spark.springbootlesson.controller;

import com.spark.springbootlesson.entity.UserEntity;
import com.spark.springbootlesson.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author initiald0824
 * @date 2019/6/29 22:20
 */
@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private UserJpa userJpa;

    @RequestMapping(value = "/login")
    public String login(UserEntity user, HttpServletRequest request) {
        // 登录成功
        boolean flag = true;
        String result = "登录成功";
        // 根据用户名查询用户是否存在
        Optional<UserEntity> userEntityOptional = userJpa.findOne(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("name"), user.getName()));
                return null;
            }
        });
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            if (!userEntity.getPwd().equals(user.getPwd())) {
                flag = false;
                result = "用户密码不相符，登录失败";
            }
        } else {
            flag = false;
            result = "用户不存在，登录失败";
        }
        if (flag) {
            // 将用户写入session
            request.getSession().setAttribute("_session_user", userEntityOptional.get());
        }
        return result;
    }
}
