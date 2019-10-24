package com.my.shop.config;

import com.my.shop.config.interceptor.CommonInterceptor;
import com.my.shop.config.interceptor.LoginInterceptor;
import com.my.shop.config.interceptor.PermissionInterceptor;
import com.my.shop.config.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * <p>
 * <tt>配置自定义SpringMVC路由处理</tt>
 * <tt>配置注册自定义拦截器执行顺序</tt>
 * </p>
 *
 * @author liu.yucheng
 * Date: 2019-10-22  14:05
 * @version 1.0
 */
@Configuration
public class WebmvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMappingExtend();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**").order(1);
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").order(2);
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**").order(3);
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").order(4);
    }
}
