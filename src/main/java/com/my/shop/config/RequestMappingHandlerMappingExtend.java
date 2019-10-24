package com.my.shop.config;

import com.my.shop.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * <p>重载路由寻址方法,根据根据当前线程处理HandlerMethod信息，获取自定义注解信息,
 * 以及本次请求变量参数信息缓存在RequestContext中
 * </p>
 *
 * @author liu.yucheng
 * Date: 2019-10-22  14:16
 * @version 1.0
 * @see com.my.shop.context.RequestContext
 * @see com.my.shop.config.annotation.MonitorConfig
 * @see com.my.shop.config.annotation.PermissionConfig
 */
public class RequestMappingHandlerMappingExtend extends RequestMappingHandlerMapping {
    private static final Logger logger = LoggerFactory.getLogger(RequestMappingHandlerMappingExtend.class);

    @Override
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
        HandlerMethod handlerMethod = super.lookupHandlerMethod(lookupPath, request);
        if(handlerMethod==null){
            logger.error("没找到方法!");
            return null;
        }
        try {
            Method invokeMethod = handlerMethod.getMethod();
            RequestContext.initRequestMonitor(invokeMethod,lookupPath);
            RequestContext.initRequestContext(request);
        } catch (Exception e) {
            logger.error("配置上下文失败！");
        }
        return handlerMethod;
    }

}
