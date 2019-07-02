package com.spark.springbootlesson.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author initiald0824
 * @date 2019/7/2 9:40
 */
public class SessionInterceptor implements HandlerInterceptor {

    private final static String LOGIN_URI = "/user/login";
    private final static String LOGIN_VIEW_URI = "/user/login_view";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String URI = request.getRequestURI();
        System.out.println("URI="+URI);
        if (LOGIN_URI.equals(URI) || LOGIN_VIEW_URI.equals((URI))) {
            return true;
        }
        // 验证session是否存在
        Object obj = request.getSession().getAttribute("_session_user");
        if (obj == null) {
            response.sendRedirect(LOGIN_VIEW_URI);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
