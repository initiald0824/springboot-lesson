package com.spark.springbootlesson.controller;

import com.alibaba.fastjson.JSONObject;
import com.spark.springbootlesson.utils.LoggerUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author initiald0824
 * @date 2019/7/2 17:04
 */
@RestController
public class TestController {

    @RequestMapping(value = "/index/login", method = RequestMethod.GET)
    public JSONObject login(HttpServletRequest request, String name, HttpServletResponse response) throws Exception {
        JSONObject obj = new JSONObject();
        response.setContentType("text/html;charset=utf-8");
        obj.put("msg", "用户: " + name + ", 登录成功。");
        request.setAttribute(LoggerUtils.LOGGER_RETURN, obj);
        return obj;
    }
}
