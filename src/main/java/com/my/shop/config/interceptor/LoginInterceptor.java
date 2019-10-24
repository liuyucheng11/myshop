package com.my.shop.config.interceptor;

import com.my.shop.config.annotation.MonitorConfig;
import com.my.shop.context.RequestContext;
import com.my.shop.context.Session;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>登录拦截,从缓存中获取登录信息</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-22  13:57
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final String REJECT_REASON = "需要登录！";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MonitorConfig config = RequestContext.getAnnotationConfig().getMonitorConfig();
        Session session = RequestContext.getRequestLocal().getSession();
        if (config == null) {
            return true;
        }
        if (config.needLogin() && session.getLoginUser() != null) {
            return true;
        } else {
            RequestContext.Interceptor interceptor = new RequestContext.Interceptor(this, REJECT_REASON);
            RequestContext.initInterceptor(interceptor);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
