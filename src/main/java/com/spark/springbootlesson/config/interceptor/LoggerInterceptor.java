package com.spark.springbootlesson.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.spark.springbootlesson.entity.LoggerEntity;
import com.spark.springbootlesson.jpa.LoggerJpa;
import com.spark.springbootlesson.utils.LoggerUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author initiald0824
 * @date 2019/7/2 14:12
 */
public class LoggerInterceptor implements HandlerInterceptor {

    private static final String LOGGER_SEND_TIME = "_send_time";

    private static final String LOGGER_ENTITY = "_logger_entity";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoggerEntity logger = new LoggerEntity();
        String sessionId = request.getRequestedSessionId();
        String url = request.getRequestURI();
        String paramData = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);

        logger.setClientIp(LoggerUtils.getClientIp(request));
        logger.setMethod(request.getMethod());
        logger.setType(LoggerUtils.getRequestType(request));
        logger.setParamData(paramData);
        logger.setUri(url);
        logger.setSessionId(sessionId);
        request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
        request.setAttribute(LOGGER_ENTITY, logger);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        int status = response.getStatus();
        long currentTime = System.currentTimeMillis();
        long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());
        LoggerEntity loggerEntity = (LoggerEntity) request.getAttribute(LOGGER_ENTITY);
        loggerEntity.setTimeConsuming(Integer.valueOf((currentTime - time) + ""));
        loggerEntity.setReturnTime(currentTime+"");
        loggerEntity.setHttpStatusCode(status + "");
        loggerEntity.setReturnData(JSON.toJSONString(request.getAttribute(LoggerUtils.LOGGER_RETURN),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue));
        LoggerJpa loggerJpa = getDao(LoggerJpa.class, request);
        loggerJpa.save(loggerEntity);
    }

    private <T> T getDao(Class<T> clazz, HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }
}
