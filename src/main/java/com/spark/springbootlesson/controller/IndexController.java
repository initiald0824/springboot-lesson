package com.spark.springbootlesson.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author initiald0824
 * @date 2019/7/2 9:36
 */
@Controller
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * 初始化登录页面
     * @return
     */
    @RequestMapping(value = "/login_view", method = RequestMethod.GET)
    public String loginView() {
        return "login";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        logger.debug("记录debug日志");
        logger.info("访问了index方法");
        logger.error("记录error错误日志");
        return "index";
    }
}
