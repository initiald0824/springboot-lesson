package com.spark.springbootlesson.controller;

import com.spark.springbootlesson.entity.DemoEntity;
import com.spark.springbootlesson.entity.UserEntity;
import com.spark.springbootlesson.jpa.UserJpa;
import com.spark.springbootlesson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @author initiald0824
 * @date 2019/6/28 16:52
 */
@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserJpa userJpa;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> list() {
        return userService.list();
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public UserEntity save(UserEntity entity) {
        return userJpa.save(entity);
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public List<UserEntity> delete(Long id) {
        userJpa.deleteById(id);
        return userJpa.findAll();
    }

    @RequestMapping(value = "/age")
    public List<UserEntity> age() {
        return userJpa.nativeQuery(20);
    }

    @RequestMapping(value = "/deleteWhere")
    public String deleteWhere() {
        userJpa.deleteQuery("admin", "123456");
        return "自定义SQL删除数据成功";
    }

    @RequestMapping(value = "/cut_page")
    public List<UserEntity> cutPage(int page) {
        UserEntity user = new UserEntity();
        user.setSize(2);
        user.setSord("desc");
        user.setPage(page);

        Sort.Direction sort_direction = Sort.Direction.ASC.toString().
                equalsIgnoreCase(user.getSord()) ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = new Sort(sort_direction, user.getSidx());
        PageRequest pageRequest = PageRequest.of(user.getPage() - 1, user.getSize(), sort);
        return userJpa.findAll(pageRequest).getContent();
    }

    @RequestMapping(value = "/validator")
    public String validator(@Valid DemoEntity entity, BindingResult result) {
        if (result.hasErrors()) {
            StringBuffer msg = new StringBuffer();

            List<FieldError> fieldErrors = result.getFieldErrors();
            Locale currentLocale = LocaleContextHolder.getLocale();
            for (FieldError fieldError: fieldErrors) {
                String errorMessage = messageSource.getMessage(fieldError, currentLocale);
                msg.append(fieldError.getField() + ": " + errorMessage + " ,");
            }
            return msg.toString();
        }
        return "验证通过, " + "名称：" + entity.getName() + "年龄：" + entity.getAge() + "邮箱地址: " + entity.getMail();
    }
}
