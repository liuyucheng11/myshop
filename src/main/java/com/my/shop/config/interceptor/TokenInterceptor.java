package com.my.shop.config.interceptor;

import com.my.shop.config.annotation.TokenConfig;
import com.my.shop.context.RequestContext;
import com.my.shop.utils.SessionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 1.拦截器实现防止短时间内重复提交
 * 基于redis实现,默认过期时间为200ms，前台请求传入时，后台对应同一用户生成token，
 * token生成规则为当前 <tt>会话用户id + "_" + 请求uri</tt>，针对短时间内重复请求同一 url，只有第一个获得锁的请求，可进行业务处理
 * 否则刷新redis锁存在时间，此拦截器预处理返回false
 * 只有获得redis锁并且业务结束后，在完成请求后释放锁
 * 2.同时@TokenConfig可自定义redis锁过期时间
 * 3.配合@Monitorconfig使用，否则会出现空指针异常
 * </p>
 *
 * @author liu.yucheng
 * Date: 2019-10-24  14:45
 * @version 1.0
 * @see com.my.shop.config.annotation.TokenConfig
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TokenConfig config = RequestContext.getAnnotationConfig().getTokenConfig();
        if (config == null) return true;
        //生成token缓存在 redis中
        String key = SessionUtils.generateKey();
        int expireTime = config.time();
        //判断当前线程是否获得锁 false当前未获得锁代表该请求
        if (SessionUtils.checkToken(key, expireTime)) {
            RequestContext.getAnnotationConfig().setLock(true);
            return true;
        } else {
            RequestContext.Interceptor interceptor = new RequestContext.Interceptor(this, null);
            RequestContext.initInterceptor(interceptor);
            SessionUtils.expireToken(key, expireTime);
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
