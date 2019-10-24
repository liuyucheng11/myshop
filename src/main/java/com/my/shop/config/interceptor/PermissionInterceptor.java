package com.my.shop.config.interceptor;

import com.my.shop.config.annotation.PermissionConfig;
import com.my.shop.context.RequestContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>权限拦截器</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-22  17:27
 * @version 1.0
 */
public class PermissionInterceptor implements HandlerInterceptor {
    private static final String REJECT_REASON = "权限不足!";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        PermissionConfig config = RequestContext.getAnnotationConfig().getPermissionConfig();
        if (config == null) {
            return true;
        }
        Set<String> role = RequestContext.getRequestLocal().getSession().getLoginUser().getPermission();
        String[] need = config.needPermission();
        Set<String> set = new HashSet<>(Arrays.asList(need));
        if (set.isEmpty()) {
            return true;
        }
        for (String p : role) {
            if (set.contains(p)) return true;
        }
        RequestContext.Interceptor interceptor = new RequestContext.Interceptor(this, REJECT_REASON);
        RequestContext.initInterceptor(interceptor);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
